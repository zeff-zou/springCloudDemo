package com.clouddemo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zzf on 2017/3/7.
 */
@RestController
public class RabbitmqController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
}
