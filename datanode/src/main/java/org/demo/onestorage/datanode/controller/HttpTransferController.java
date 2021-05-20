package org.demo.onestorage.datanode.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class HttpTransferController {

    @PostMapping("/transfer/http")
    public void receive(
            @RequestParam("basePackId")Integer basePackId,
            @RequestParam("basePackPortionId")Integer basePackPortionId,
            @RequestParam("file")MultipartFile file){
        System.out.println(file.getSize());
    }



    @GetMapping("/transfer/http")
    public MultipartFile send(
            @RequestParam("basePackId")Integer basePackId
    ){
        System.out.println("RECEIVED");
        return null;
    }




}
