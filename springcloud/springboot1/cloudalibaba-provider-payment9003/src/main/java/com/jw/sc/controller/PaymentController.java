package com.jw.sc.controller;

import com.jw.sc.entity.CommonResult;
import com.jw.sc.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1L,new Payment(1L,"1lishflalliaslvnldksnvlaefil"));
        hashMap.put(2L,new Payment(2L,"2vnldksnvlaefillishflalliasl"));
        hashMap.put(3L,new Payment(3L,"3aslvnldksnvlaefillishflalli"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long  id) {

        Payment payment = hashMap.get(id);
        CommonResult<Payment> result  = new CommonResult<>
                (200,"from mysql, server port: " + serverPort,payment);

        return result;
    }
}
