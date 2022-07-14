package com.xlf.account.service;

public interface RedisLockExecutor<T> {
    T execute();
}
