package com.jw.sc.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
public class SendMessageController {

    @Value("${server.port}")
    private String serverPort;

//    @Resource
//    private IMessageProvider messageProvider;

//    @GetMapping(value = "/sendmessage")
//    public String sendMessage(){
//        String message = "这是8801服务发送： server port:  "+  serverPort;
//        System.out.println(message);
//        return messageProvider.sendsms(message);
//    }

    @Resource
    private StreamBridge streamBridge;

    @RequestMapping("/sendsms1")
    public String sendsms1() {
        streamBridge.send("sms1-out-0", "message1: "+LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return "server port:  "+ serverPort;
    }

    @RequestMapping("/sendsms2")
    public String sendsms2() {
        streamBridge.send("sms2-out-0", "message2: "+new Date());
        return "server port:  "+ serverPort;
    }

}
