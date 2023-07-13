package com.jw.sc.controller;

import com.jw.sc.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod") //使用全局的fallback
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_Ok(@PathVariable("id") Integer id){

        String result = paymentHystrixService.paymentInfo_Ok(id);
        log.info("*************result: " +result +"\t");
        return result;
    }

    //访问等待超时或自己出问题，不想等超过1.5s；就fallback
    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfoTimeout_FallbackMethod",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000")})
    @HystrixCommand //使用默认的全局fallback
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){

        //int age = 10/0;//模拟自己出错，fallback
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        log.info("*************result: " +result +"\t");
        return result;
    }

    public String paymentInfoTimeout_FallbackMethod(@PathVariable("id") Integer id){

        log.info("***我是Hystrix消费端80；对方支付系统繁忙，10s再试；或自己运行出错，请检查代码");
        return "***我是Hystrix消费端80；对方支付系统繁忙，10s再试；或自己运行出错，请检查代码";
    }

    //下面是全局的fallback
    public String payment_Global_FallbackMethod(){

        log.info("Global异常处理信息，请稍后再试");
        return "Global异常处理信息，请稍后再试";
    }
}
