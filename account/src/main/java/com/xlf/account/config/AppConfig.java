package com.xlf.account.config;

import com.xlf.common.util.SnowflakeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

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

}
