package com.xlf.trade.service;

public interface RedisLockExecutor<T> {
    T execute();
}
