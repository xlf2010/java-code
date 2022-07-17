package com.xlf.common.lock;

public interface RedisLockService {
    <T> T runInRedisLock(String lockKey, RedisLockExecutor<T> executorService);
}
