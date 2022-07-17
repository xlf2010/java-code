package com.xlf.trade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * starter
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("com.xlf.trade.repository.mapper")
@EnableScheduling
@ComponentScan(basePackages = "com.xlf")
public class TradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }
}
