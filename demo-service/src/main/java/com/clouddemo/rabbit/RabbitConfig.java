package com.clouddemo.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zzf on 2017/3/7.
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue textRoutingQueue() {
        return new Queue("textRouting");
    }
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }
}
