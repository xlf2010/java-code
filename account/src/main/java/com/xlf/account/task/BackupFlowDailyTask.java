package com.xlf.account.task;

import com.xlf.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class BackupFlowDailyTask {
    @Resource
    private AccountService accountService;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void backup() {
        log.info("starting back up account flow...");
        accountService.checkAndBackupAccountFlow();
        log.info("end back up account flow...");
    }
}
