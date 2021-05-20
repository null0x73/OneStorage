package org.demo.onestorage.registry.controller;

import org.demo.onestorage.model.config.StorageConfig;
import org.demo.onestorage.model.metadata.Metadata;
import org.demo.onestorage.registry.service.DuplicateService;
import org.demo.onestorage.registry.service.InboxService;
import org.demo.onestorage.registry.service.RegistryService;
import org.demo.onestorage.util.format.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class InboxController {


    @Autowired
    InboxService inboxService;

    @Autowired
    RegistryService registryService;

    @Autowired
    DuplicateService duplicateService;

    /**
     *
     * @param file
     * @param metadataJson
     * @throws IOException
     *
     * 这个 Inbox 功能是与 Buffer 集成的。数据从客户端提交后，首先进入 Buffer / Inbox 来处理。
     *
     * 在 Inbox 阶段主要是临时存储于 Buffer 里，进行注册、元数据标签记录、partion 分布写入等事务。全部完成后，再从 Inbox 删除。
     *
     */
    @PostMapping("/inbox/http")
    public String receivePackInbox(
            @RequestParam("file") MultipartFile file,
            @RequestParam("metadataJson") String metadataJson,   // SAMPLE: metadataJson = "Metadata{tags=[nature, youtube, camp, scenery], values={video-format=mkv, resolution=1080p}}";
            @RequestParam("storageConfigJson") String storageConfigJson
    ) throws IOException {


//        // 调试部分 ————————————————————————————————————————————————————————————————————
//        // 做个测试，直接存桌面。【 “ADMIN” 需要改成自己的用户名，不然会报错】
//        FileUtil.writeBinaryFile("C:/Users/ADMIN/Desktop", file.getOriginalFilename(), file.getBytes());
//
//        // 元数据 JSON
//        System.out.println(metadataJson);
//
//        // 元数据对象
//        Metadata metadata = JsonUtil.getObjectFromJson(metadataJson, Metadata.class);
//
//        DebugUtil.printWithIdentityHashCode(metadata);
//
//
//        StorageConfig storageConfig = JsonUtil.getObjectFromJson(storageConfigJson, StorageConfig.class);
//
//        DebugUtil.printWithIdentityHashCode(storageConfig);
//
//        // 结束调试部分 ————————————————————————————————————————————————————————————————





        // 处理 JSON 反序列化到对象
        Metadata metadata = JsonUtil.getObjectFromJson(metadataJson, Metadata.class);
        StorageConfig storageConfig = JsonUtil.getObjectFromJson(storageConfigJson, StorageConfig.class);


        // 获取文件大小
        long fileSize = file.getSize();


        // 分配一个 BasePackId 用于标记待存储的当前文件（刚进入 Inbox 的这个 BasePack，此时他还没有 ID）。
        // 这个 ID 分配建议用数据库自增主键来做。
        // 除了描述内容的 metadata 用于索引外，还需要记录文件大小 filesize。
        // 这一步就要在 BasePack 表中新建一行记录
        int newBasePackId = registryService.registerNewBasePack(metadata, fileSize);


        // 以分配到的这个 id 作为文件夹名，在根目录下创建这个新文件夹作为 basePack 目录。把这个单个文件放进去。
        // 例如，根目录取 "C:/Buffer0", 收到一个 2021.jpg ，分配了 170001 的 id，就该写入 "C:/Buffer0/170001/2021.jpg" 文件。
        inboxService.saveFileToBasePackInBuffer();


        // 向注册中心（Registry）报告，把这个 pack 状态更新为：已收入缓冲区 / BUFFERED（2/3）
        registryService.reportBasePackInboxDone(newBasePackId);


        // 后面就是冗余复制的事情了。由于比较耗时，所以先返回结果告诉客户端保存成功了，在新开的线程中另外执行复制任务。（后期考虑改成线程池）
        ((new Thread(new Runnable() {
            @Override
            public void run() {
                duplicateService.executeDuplicate(newBasePackId, storageConfig);
            }
        }))).start();


        // 发回一个成功的响应。JSON 格式，内容待定，这个乱写的。
        return "{\"status\":success}";

    }




}
