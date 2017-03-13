package com.clouddemo.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by zzf on 2017/3/7.
 */
@Component
public class Receiver {
    @RabbitListener(queues = "textRouting")
    public void process(String hello) {
        System.out.println("service-Receiver : " + hello);
    }

    @RabbitListener(queues = "hello")
    public void processhello(String hello) {
        System.out.println("service-Receiver-hello : " + hello);
    }
}
