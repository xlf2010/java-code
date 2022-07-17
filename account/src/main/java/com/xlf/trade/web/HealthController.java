package com.xlf.trade.web;

import com.xlf.trade.logging.LogOff;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HealthController {
    @RequestMapping("health")
    @LogOff
    public String health() {
        log.info("health check UP");
        return "UP";
    }
}
