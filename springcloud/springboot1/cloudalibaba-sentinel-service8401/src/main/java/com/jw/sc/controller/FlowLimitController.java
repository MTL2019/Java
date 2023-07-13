package com.jw.sc.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {

    @GetMapping(value = "/testA")
    public String testA(){
        return "---------- test A";
    }

    @GetMapping(value = "/testB")
    public String testB(){
        return "---------- test B";
    }

    //与热点规则配置配合，主要目的是设置兜底方法，利用@SentinelResource注解
    //如果采用热点限流，但没有设置兜底方法，会弹报错页面
    //value:限流标识的资源名,如果违背规则，则由blockHandler对应的方法兜底
    //自定义兜底方法，类似于@HystrixCommand
    @GetMapping(value = "/testhotkey")
    @SentinelResource(value = "testhotkey",blockHandler = "deal_testhotkey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                            @RequestParam(value = "p2",required = false)String p2)
    {
        return "---------- testHotKey";
    }

    public String deal_testhotkey(String p1, String p2, BlockException exception)
    {
        return "---------- deal_testhotkey";
        //sentinel默认提示页面都是 blocked by sentinel(flow limiting)
    }
}
