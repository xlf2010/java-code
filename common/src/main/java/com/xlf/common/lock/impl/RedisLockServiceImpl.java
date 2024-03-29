package com.xlf.common.lock.impl;

import com.xlf.common.exception.ErrorCodeEnum;
import com.xlf.common.lock.RedisLockExecutor;
import com.xlf.common.lock.RedisLockService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisLockServiceImpl implements RedisLockService {
    @Resource
    private RedissonClient redissonClient;

    @Override
    public <T> T runInRedisLock(String lockKey, RedisLockExecutor<T> executorService) {
        RLock lock = redissonClient.getLock(lockKey);
        boolean lockSuccess = false;
        try {
            lockSuccess = lock.tryLock();
            if (!lockSuccess) {
                throw ErrorCodeEnum.DATA_PROCESSING.newException();
            }
            return executorService.execute();
        } finally {
            if (lockSuccess) {
                lock.unlock();
            }
        }
    }
}
