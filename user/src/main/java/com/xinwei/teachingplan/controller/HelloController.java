package com.xinwei.teachingplan.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: HelloController
 * @Author chengfei
 * @Date 2020/6/17 19:56
 * @Version 1.0
 * @Description: TODO
 **/
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
