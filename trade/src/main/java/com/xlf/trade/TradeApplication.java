package com.xlf.trade;

import com.xlf.trade.config.FeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
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
@EnableFeignClients(basePackages = "com.xlf.trade.remote", defaultConfiguration = FeignConfiguration.class)
public class TradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }
}
