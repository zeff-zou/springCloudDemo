package com.clouddemo.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zzf on 2017/2/13.
 */
@Component
public class ComputeClientHystrix implements ComputeClient{

    @Override
    public Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        return -9999;
    }
}
