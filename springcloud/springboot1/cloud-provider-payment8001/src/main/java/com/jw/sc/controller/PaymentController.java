package com.jw.sc.controller;

import com.jw.sc.entity.CommonResult;
import com.jw.sc.entity.Payment;
import com.jw.sc.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")//${server.port}
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;//服务发现对象

    //RequestBody接受前端请求体中的数据，缺少会导致创建的键值为空
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);

        log.info("******插入结果： " + result);
        if (result > 0) {
            return new CommonResult(200,"插入数据库成功，服务提供者端口号为： " + serverPort,result);
        }else{
            return new CommonResult(404,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);

        log.info("******查询结果: " + payment);
        if (payment != null) {
            return new CommonResult(200,"查询成功，服务提供者端口号为： "+ serverPort,payment);
        }else{
            return new CommonResult(404,"没有对应记录，查询ID: " + id,null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLb(){
        return serverPort;
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();

        for (String element:services) {
            log.info("********element:   " + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance: instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost()+ "\t" + instance.getPort()+ "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    //为geign设置超时等待的测试接口
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return serverPort;
    }

    //访问链路追踪接口
    @PostMapping(value = "/payment/zipkin")
    public String paymentZipkin() {
        return "Hi, I am payment zipkin server fallback";
    }
}
