package com.jw.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients  //开启feign支持
public class NacosOrderConsumerMain84 {

    public static void main(String[] args) {
        SpringApplication.run(NacosOrderConsumerMain84.class, args);
    }

}