package com.milo.automation.atframework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.milo.automation.atframework.mapper")

public class AtframeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtframeworkApplication.class, args);
    }

}
