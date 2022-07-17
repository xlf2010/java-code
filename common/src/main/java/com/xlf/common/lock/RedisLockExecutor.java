package com.xlf.common.lock;

public interface RedisLockExecutor<T> {
    T execute();
}
