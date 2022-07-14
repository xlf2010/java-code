package com.xlf.account.service;

public interface RedisLockService {
    <T> T runInRedisLock(String lockKey, RedisLockExecutor<T> executorService);
}
