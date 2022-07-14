package com.xlf.account.config;

import com.xlf.common.util.SnowflakeUtil;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class AppConfig {
    private Environment environment;

    @Value("${snowFlake.workerId}")
    private Long workerId;
    @Value("${snowFlake.centerId}")
    private Long centerId;

    @PostConstruct
    public void initSnowFlake() {
        SnowflakeUtil.init(workerId, centerId);
    }

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                10,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1000),
                new BasicThreadFactory.Builder().namingPattern("account-thread-%d").build(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        return executor;
    }
}
