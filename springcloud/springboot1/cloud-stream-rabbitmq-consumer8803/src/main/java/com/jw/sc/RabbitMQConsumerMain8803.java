package com.jw.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
@EnableEurekaClient
public class RabbitMQConsumerMain8803
{
    public static void main(String[] args) {
        SpringApplication.run(RabbitMQConsumerMain8803.class, args);
    }


    @Bean
    public Consumer<String> sms1() {
        return System.out::println;
    }

//    @Bean
//    public Consumer<Date> sms2() {
//        return System.out::println;
//    }
}
