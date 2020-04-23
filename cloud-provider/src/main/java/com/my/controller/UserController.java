package com.my.controller;

import com.my.dao.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/findUser/{id}")
    public User findById(@PathVariable Long id) {
        System.out.println("--------------start1");
        User findOne = new User();
        findOne.setName("hello zhangsan"+id);
        findOne.setId(id.toString());
        System.out.println("--------------end");
        return findOne;
    }
}
