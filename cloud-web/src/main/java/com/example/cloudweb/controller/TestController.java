package com.example.cloudweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping("home")
    public String home(){
        return "home";
    }

    @RequestMapping("test")
    @ResponseBody
    public String test(){
        System.out.println("请求已执行");
        return "success";
    }
}
