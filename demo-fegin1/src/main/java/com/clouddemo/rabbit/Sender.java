package com.clouddemo.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by zzf on 2017/3/7.
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "textRouting-1 " + new Date();
        System.out.println("Sender-1 : " + context);
        this.rabbitTemplate.convertAndSend("textRouting", context);
    }

    public void sendHello() {
        String context = "hello-1 " + new Date();
        System.out.println("hello-1 : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}
