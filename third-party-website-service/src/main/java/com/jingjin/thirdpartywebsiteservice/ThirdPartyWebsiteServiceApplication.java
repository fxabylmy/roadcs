package com.jingjin.thirdpartywebsiteservice;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.jingjin.**")
@EnableMethodCache(basePackages = "com.jingjin")
@EnableCreateCacheAnnotation
public class ThirdPartyWebsiteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThirdPartyWebsiteServiceApplication.class, args);
    }

}
