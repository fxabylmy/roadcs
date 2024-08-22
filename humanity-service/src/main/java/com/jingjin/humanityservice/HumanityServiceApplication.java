package com.jingjin.humanityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.jingjin.**")
public class HumanityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HumanityServiceApplication.class, args);
    }

}
