package com.xlf.trade.service;

public interface RedisLockService {
    <T> T runInRedisLock(String lockKey, RedisLockExecutor<T> executorService);
}
