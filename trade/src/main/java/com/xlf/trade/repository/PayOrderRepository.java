package com.xlf.trade.repository;

import com.xlf.trade.repository.mapper.PayOrderMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class PayOrderRepository {
    @Resource
    private PayOrderMapper payOrderMapper;

}
