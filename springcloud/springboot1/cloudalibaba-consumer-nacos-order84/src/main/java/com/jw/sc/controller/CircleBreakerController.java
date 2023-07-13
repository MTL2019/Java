package com.jw.sc.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jw.sc.entity.CommonResult;
import com.jw.sc.entity.Payment;
import com.jw.sc.service.PaymentService;
import jdk.jshell.EvalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {

    private static final String SERVICE_URL = "http://nacos-payment-provider";

//    @Value("${server-url.nacos-user-server}")
//    private String serverURL;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback") //没有兜底
    //@SentinelResource(value = "fallback",fallback = "handlerFallback") //fallback兜底运行异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler")   //blockHandler兜底sentinel配置违规异常
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",
                        exceptionsToIgnore = {IllegalArgumentException.class}) //两个都配，违规还是违规管
                        //exceptionsToIgnore：此设置的错误不再降级，直接报错，走error page
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {

        CommonResult<Payment> result =  restTemplate.getForObject(SERVICE_URL+"/paymentSQL/"+id,CommonResult.class,id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException, 非法参数异常");
        }else if(result.getData() == null){
            throw new NullPointerException("NullPointerException,空指针异常;数据库没有对应记录ID");
        }
        return result;
    }

    //fallback兜底方法，针对java运行异常
    public CommonResult handlerFallback(@PathVariable("id")Long id,Throwable e){

        Payment payment = new Payment(id,"null");
        return new CommonResult<>(444,"兜底异常 handlerFallback，Exception内容" +e.getMessage(),payment);
    }

    //blockHandler，针对sentinel页面配置的违规异常兜底
    public CommonResult blockHandler(@PathVariable("id")Long id, BlockException e){

        Payment payment = new Payment(id,"null");
        return new CommonResult<>(445,"兜底异常 blockHandler，BlockException" +e.getMessage(),payment);
    }

    ///////////openFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        return paymentService.paymentSQL(id);
    }
}
