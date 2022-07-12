package com.xlf.account.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HealthController {
    @RequestMapping("health")
    public String health() {
        log.info("health check result:UP");
        return "UP";
    }
}
