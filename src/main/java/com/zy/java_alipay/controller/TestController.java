package com.zy.java_alipay.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhangyao
 * @create:2020-03-19 14:51
 **/
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(String message){
        return 12+message;

    }
}
