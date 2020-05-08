package com.example.aliconsumer.feign;

import com.example.aliconsumer.dao.User;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//测试修改
//@FeignClient(name = "ali-provider", fallbackFactory = FeignClientFallbackFactory.class)
@FeignClient(name = "ali-provider")
public interface UserFeignClient {
    @RequestMapping(value = "/findUser/{id}", method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);
    @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
    String echo(@PathVariable("string") String string);
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
//                user.setName("进入断路器");
//                return user;
//            }
//        };
//    }
//}
