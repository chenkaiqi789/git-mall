package com.gec.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.gec.mall.mapper")
@EnableEurekaClient
public class MSApp {
    public static void main(String[] args) {
        SpringApplication.run(MSApp.class, args);
    }
}
