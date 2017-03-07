package com.clouddemo.web;

import com.clouddemo.rabbit.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zzf on 2017/3/7.
 */
@RestController
public class RabbitmqController {
    @Autowired
    private Sender sender;
    @RequestMapping("/hello")
    public String index() {
        sender.send();
        return "Hello World";
    }
}
