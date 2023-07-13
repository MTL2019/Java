package com.jw.sc.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced  //负载均衡,注意别加错了位置
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
