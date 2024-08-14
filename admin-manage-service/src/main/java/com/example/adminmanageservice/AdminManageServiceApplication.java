package com.example.adminmanageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.jingjin.**")
public class AdminManageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminManageServiceApplication.class, args);
    }

}
