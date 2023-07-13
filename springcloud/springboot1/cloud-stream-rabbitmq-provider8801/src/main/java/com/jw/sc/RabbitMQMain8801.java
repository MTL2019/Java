package com.jw.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RabbitMQMain8801
{
    public static void main(String[] args) {
        SpringApplication.run(RabbitMQMain8801.class, args);
    }

//    @Bean
//    public Function<Date> sms() {
//        return () -> new Date();
//    }
}
