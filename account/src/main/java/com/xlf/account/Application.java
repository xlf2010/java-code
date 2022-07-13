package com.xlf.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * starter
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("com.xlf.account.repository.mapper")
@ComponentScan(basePackages = "com.xlf")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
