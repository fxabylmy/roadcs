package com.jingjin.userwebsiteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.jingjin.**") // bean扫描范围
@EnableFeignClients(basePackages = {"com.jingjin.serviceClient"}) // 声明feign客户端
public class UserWebsiteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserWebsiteServiceApplication.class, args);
    }

}
