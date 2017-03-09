package com.clouddemo.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by zzf on 2017/3/9.
 */
@Component
@RabbitListener(queues = "hello")
public class helloReceiver {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("helloReceiver : " + hello);
    }
}
