package com.jw.sc.controller;

import com.jw.sc.domain.Order;
import com.jw.sc.entity.CommonResult;
import com.jw.sc.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;


    @GetMapping(value = "/order/create")
    public CommonResult createOrder(Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}
