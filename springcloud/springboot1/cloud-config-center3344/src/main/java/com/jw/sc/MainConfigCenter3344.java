package com.jw.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer //开启config功能
public class MainConfigCenter3344 {
    public static void main(String[] args) {
        SpringApplication.run(MainConfigCenter3344.class,args);
    }
}
