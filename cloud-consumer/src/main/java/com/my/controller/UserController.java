package com.my.controller;

import com.my.dao.User;
import com.my.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guolei
 */
@RestController
public class UserController {
    public static final long COM=5;
    @Autowired
    private UserFeignClient userFeignClient;
    @RequestMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        System.out.println("----------即将传入远程调用id："+id);
        return userFeignClient.findById(id);
    }
    @RequestMapping("/test/{id}")
    public String test(@PathVariable Long id) {
        System.out.println("----------打印id："+id);
        return id.toString();
    }

    public static void main(String[] args) {
//        changed
        System.out.println(args[1]);
    }
}
