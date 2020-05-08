package com.example.aliprovider.controller;


import com.example.aliprovider.dao.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Value("${good}")
    private String name;

    @GetMapping("/findUser/{id}")
    public User findById(@PathVariable Long id) {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("--------------start1");
        User findOne = new User();
        findOne.setName("hello ali-zhangsan"+id);
        findOne.setId(id.toString());
        System.out.println("--------------end");
        return findOne;
    }

    @RequestMapping("/test-config")
    public String getConfigName() {
        System.out.println("--------------getName:"+name);
        return name;
    }

    @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) {
        return "Hello Nacos Discovery " + string;
    }
}
