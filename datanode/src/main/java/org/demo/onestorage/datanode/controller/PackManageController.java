package org.demo.onestorage.datanode.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PackManageController {


    @GetMapping("manage/checkPortionByPack")
    public int checkPortionIfExist(
            @RequestParam("basePackId")Integer basePackId
    ){
        return basePackId;      // -1 代表找不到。否则返回 portionId（0或正整数）
    }



    @DeleteMapping("/manage/delete")
    public void deletePortionIfExist(
            @RequestParam("basePackId")Integer basePackId
    ){

        // 返回删除结果。是否存在、若存在，是否成功删除。

    }


    @GetMapping("/manage/checkPortionOfAll")
    public void checkPortionOfAll(){

        // 返回当前机器（数据节点）上所有存储的文件信息。每一条包含 packId 和 partionId。

    }


}
