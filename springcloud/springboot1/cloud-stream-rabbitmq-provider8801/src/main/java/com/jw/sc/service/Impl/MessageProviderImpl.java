package com.jw.sc.service.Impl;

import com.jw.sc.service.IMessageProvider;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

//@EnableBinding(Source.class)  //定义消息的推送管道
@Service
public class MessageProviderImpl implements IMessageProvider {

//    @Resource
//    private MessageChannel output;

    @Override
    public String send() {

        String serial = UUID.randomUUID().toString();
       // output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("***serial:" + serial);
        return null;
    }

//    @Resource
//    private final sendsms streamBridge;

    /**
     * 采用StreamBridge的发送方式
     *
     * @param message 　短消息
     * @link https://docs.spring.io/spring-cloud-stream/docs/3.1.0/reference/html/spring-cloud-stream.html#_binding_and_binding_names
     */
//    @Override
//    public void sendsms(String message) {
//        streamBridge.send("sendsms-out-0", message);
//    }

}
