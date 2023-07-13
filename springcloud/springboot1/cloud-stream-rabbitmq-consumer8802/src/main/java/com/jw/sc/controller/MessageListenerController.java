package com.jw.sc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Consumer;

@RestController
//@EnableBinding(Sink.class)
public class MessageListenerController {

    @Value("${server.port}")
    private String serverPort;

    //@StreamListener(Sink.INPUT)
    public void recieveMessage(Message<String> message){
        System.out.println("消费者1号，接收到的消息："+message.getPayload()+"\t"+serverPort);
    }

//    public Consumer<String> sms(){
//        return System.out::println;
//    }

}
