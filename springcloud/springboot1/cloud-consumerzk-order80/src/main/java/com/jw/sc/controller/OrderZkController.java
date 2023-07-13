package com.jw.sc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderZkController {

    //CLOUD-PROVIDER-PAYMENT 和8004 yml中注册的服务提供名字要一致，大小写一致
    public static final String PAYMENT_URL = "http://CLOUD-PROVIDER-PAYMENT";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String paymentInfo(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/zk",String.class);
    }
//
//    @GetMapping("/consumer/payment/create")
//    public CommonResult<Payment> create(Payment payment){
//        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
//    }
}
