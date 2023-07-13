package com.jw.sc.controller;

import com.jw.sc.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.ws.rs.Path;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_Ok(@PathVariable("id") Integer id){

        String result = paymentService.paymentInfo_Ok(id);
        log.info("*************result: " +result +"\t");
        return result;
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){

        String result = paymentService.paymentInfo_Timeout(id);
        log.info("*************result: " +result +"\t");
        return result;
    }

    ///////服务熔断：当多次访问id为负数，失败了达到60%以上，会造成服务熔断；此时以id>0访问页报错；多次访问后才恢复
    @GetMapping(value = "/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){

        String result = paymentService.paymentCircuitBreaker(id);
        log.info("*****result: " +result +"\t");
        return result;
    }
}
