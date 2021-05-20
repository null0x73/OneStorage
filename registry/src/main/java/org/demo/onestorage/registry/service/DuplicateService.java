package org.demo.onestorage.registry.service;

import org.demo.onestorage.model.config.StorageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class DuplicateService {


    @Autowired
    InboxService inboxService;

    @Autowired
    RegistryService registryService;


    public void executeDuplicate(int basePackId, StorageConfig storageConfig){


        // 把 inbox buffer 中的文件按照 StorageConfig 中的要求，调用 HTTP 传输服务、复制到 缓存（cache）和备份（backup）里。
        // 这里关联到 Datanode 模块的文件传输功能代码



        if(storageConfig.isCacheEnabled()) {
            // 向负责缓存的 datanode 发送
            // ......
            // 然后在数据库的 BasePackPortion 表中添加一条记录，记录这一份复制的基本文件包。
            // ......
        }



        for(int i=0; i<storageConfig.getBackupRedundancy(); i++) {
            // 向负责备份的 n 个 datanode 发送。这里假设负责 backup 的 datanode 数量总是大于 StorageConfig 中要求的数量。后期再添加报错。
            // ......
            // 然后每次完成都要数据库的 BasePackPortion 表中添加一条记录，记录这一份复制的基本文件包。
            // ......
        }


        // 向 registry 注册中心报告，当前 BasePack 的多份冗余已全部处理完毕。把此 BasePack 在数据库中记录的状态设置为 DONE（3/3）
        registryService.reportBasePackDuplicateDone(basePackId);


    }


}
