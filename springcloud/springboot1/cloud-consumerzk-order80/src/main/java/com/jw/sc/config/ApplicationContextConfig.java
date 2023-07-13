package com.jw.sc.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    //RestTemplate作用：通过RestTemplate调用8001端口的服务，spring写的模版
    //ApplicationContext.xml中注入bean <bean id="" class= "">
    @Bean  //取代xml注解方式
    @LoadBalanced  //开启负载均衡注解
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
