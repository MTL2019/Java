package com.jw.sc.controller;

import com.jw.sc.entity.CommonResult;
import com.jw.sc.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    //单机版，访问地址写死
    //public static final String PAYMENT_URL = "http://localhost:8001";
    //对于服务提供者是集群时，连接只写服务注册名，至于哪台机器响应，不确定
    //同时要在ApplicationContextConfig中restful方法中开启@LoadBalanced  //开启负载均衡注解
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    //getForObject返回的是数据转化的对象，类似于json
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id")Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    //getForEntity返回的是ResponseEntity，包含了请求头、请求题等信息;信息更详细
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id")Long id){

        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);

        if (entity.getStatusCode().is2xxSuccessful() ) {
            log.info(entity.getStatusCode() + "\t" + entity.getHeaders());
            return entity.getBody();
        }else{
            return new CommonResult(444,"操作失败");
        }
    }

    //sleuth+zipkin
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){
        return restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin",String.class);
    }


}
