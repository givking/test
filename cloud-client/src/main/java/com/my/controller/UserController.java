package com.my.controller;

import com.my.dao.User;
import com.my.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guolei
 */
@RestController
public class UserController {
    public static final long COM=5;
    @Autowired
    private UserFeignClient userFeignClient;
    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        if(id==COM){
            System.out.println("good");
        }
        System.out.println("----------aabbccff"+id);
        return userFeignClient.findById(id);
    }
    @GetMapping("/test/{id}")
    public String test(@PathVariable Long id) {
        System.out.println("----------testabc"+id);
        return id.toString();
    }

    public static void main(String[] args) {
//        changed
        System.out.println(args[1]);
    }
}
