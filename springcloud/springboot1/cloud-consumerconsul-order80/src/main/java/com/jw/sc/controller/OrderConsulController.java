package com.jw.sc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderConsulController {
    //CONSUL-PROVIDER-PAYMENT 和8006 yml中注册的服务提供名字要一致，大小写一致
    public static final String PAYMENT_URL = "http://CONSUL-PROVIDER-PAYMENT";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String paymentInfo(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/consul",String.class);
    }

}
