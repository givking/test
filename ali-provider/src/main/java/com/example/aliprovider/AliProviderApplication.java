package com.example.aliprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AliProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliProviderApplication.class, args);
    }

}
