package com.jw.sc.service;

import com.jw.sc.entity.CommonResult;
import com.jw.sc.entity.Payment;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//PaymentFallbackService是兜底方法，出错时调用;当调用的服务器关闭时会跳转
@Component
@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class) //指向要调用的微服务名称
public interface PaymentService {

    //正常调用的方法
    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id")Long id);
}
