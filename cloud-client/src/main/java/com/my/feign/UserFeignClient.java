package com.my.feign;

import com.my.dao.User;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* @FeignClient(name = "cloud-provider", fallbackFactory = FeignClientFallbackFactory.class) */
@FeignClient(name = "cloud-provider")
public interface UserFeignClient {
    @RequestMapping(value = "/findUser/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);
}

/**
 * UserFeignClient的fallbackFactory类，该类需实现FallbackFactory接口，并覆写create方法
 */
//@Component
//class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
//
//    @Override
//    public UserFeignClient create(Throwable cause) {
//        return new UserFeignClient() {
//            @Override
//            public User findById(Long id) {
//                User user = new User();
//                user.setId("1");
//                user.setName("默认用户");
//                return user;
//            }
//        };
//    }
//}
