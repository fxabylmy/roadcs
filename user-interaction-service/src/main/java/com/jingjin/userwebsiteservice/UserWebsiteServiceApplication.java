package com.jingjin.userwebsiteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.jingjin.**")
public class UserWebsiteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserWebsiteServiceApplication.class, args);
    }

}
