package com.jw.sc.service;

import org.springframework.web.bind.annotation.PathVariable;

public interface PaymentService {

    public String paymentInfo_Ok(Integer id);

    public String paymentInfo_Timeout(Integer id);

    public String paymentInfo_TimeoutHandler(Integer id);

    public String paymentCircuitBreaker(@PathVariable("id")Integer id);
}
