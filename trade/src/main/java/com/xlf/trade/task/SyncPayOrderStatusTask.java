package com.xlf.trade.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SyncPayOrderStatusTask {
    @Scheduled(cron = "0 6 12 *  * ?")
    public void backup() {
        log.info("starting back up account flow...");
    }
}
