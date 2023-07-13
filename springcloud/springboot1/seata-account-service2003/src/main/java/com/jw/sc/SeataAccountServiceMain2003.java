package com.jw.sc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源的自动配置，用我们自己的
@MapperScan({"com.jw.sc.dao"})
public class SeataAccountServiceMain2003 {

    public static void main(String[] args) {
        SpringApplication.run(SeataAccountServiceMain2003.class, args);
    }


}
