package com.jw.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosOrderConsumerMain83 {

    public static void main(String[] args) {
        SpringApplication.run(NacosOrderConsumerMain83.class, args);
    }

}

