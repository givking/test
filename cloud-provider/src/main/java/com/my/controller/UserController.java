package com.my.controller;

import com.my.dao.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
//    @Value("${name}")
//    private String name;

    @GetMapping("/findUser/{id}")
    public User findById(@PathVariable Long id) {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("--------------start1");
        User findOne = new User();
        findOne.setName("hello zhangsan"+id);
        findOne.setId(id.toString());
        System.out.println("--------------end");
        return findOne;
    }

//    @RequestMapping("/test-config")
//    public String getConfigName() {
//        System.out.println("--------------getName:"+name);
//        return name;
//    }
}
