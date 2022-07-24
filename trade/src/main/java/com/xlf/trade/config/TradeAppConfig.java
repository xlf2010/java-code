package com.xlf.trade.config;

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
public class TradeAppConfig {
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
        return new ThreadPoolExecutor(
                10,
                20,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1000),
                new BasicThreadFactory.Builder().namingPattern("trade-thread-%d").build(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }


}
