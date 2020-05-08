package com.example.aliconsumer.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @SentinelResource(value = "HelloWorld", blockHandler = "handleFlowQpsException",
            fallback = "helloWorldFallback")
    public void helloWorld(){
        System.out.println("doing");
    }

    public String handleFlowQpsException(BlockException e) {
        e.printStackTrace();
        return "handleFlowQpsException for queryOrderInfo2: ";
    }

    public String helloWorldFallback(Throwable e) {
        return "fallback helloWorldFallback: ";
    }
}
