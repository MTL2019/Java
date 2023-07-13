package com.jw.sc.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jw.sc.MyHandler.CustomerBlockHandler;
import com.jw.sc.entity.CommonResult;
import com.jw.sc.entity.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RateLimitController {

    @GetMapping(value = "/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource()
    {
        return new CommonResult(200,"按资源名称测试OK",new Payment(2020L,"serial001"));
    }

    public CommonResult handleException(BlockException exception)
    {
        String message = exception.getClass().getCanonicalName() + "\t 服务不可用";
        return new CommonResult(444, message);
    }


    @GetMapping(value = "/ratelimit/byUrl")
    public CommonResult byUrl()
    {
        return new CommonResult(200,"按客户自定义 byUrl",new Payment(2022L,"serial008"));
    }

    @GetMapping(value = "/ratelimit/customerblockhandler")
    @SentinelResource(value = "customerblockhandler",       // 资源名称
            blockHandlerClass = CustomerBlockHandler.class, //指向global handler集中处理类
            blockHandler = "HandlerException")               //对应集中处理类的方法名
    public CommonResult customerBlockHandler()
    {
        return new CommonResult(200,"按客户自定义",new Payment(2022L,"serial002"));
    }
}
