package com.jingjin.thirdpartywebsiteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.jingjin.**")
public class ThirdPartyWebsiteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThirdPartyWebsiteServiceApplication.class, args);
    }

}
