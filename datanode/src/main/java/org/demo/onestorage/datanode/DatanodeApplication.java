package org.demo.onestorage.datanode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.demo.onestorage.datanode.controller")
public class DatanodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatanodeApplication.class, args);
    }

}
