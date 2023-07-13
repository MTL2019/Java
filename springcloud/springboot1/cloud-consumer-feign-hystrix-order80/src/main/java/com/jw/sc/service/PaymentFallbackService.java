package com.jw.sc.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_Ok(Integer id) {
        return "===========PaymentFallbackService ---->>> paymentInfo_Ok";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "===========PaymentFallbackService ---->>> paymentInfo_Timeout";
    }
}
