package com.jw.sc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope  //开启动态监控
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }
}
