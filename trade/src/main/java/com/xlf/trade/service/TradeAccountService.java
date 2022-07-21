package com.xlf.trade.service;

import com.xlf.trade.vo.request.CreateTradeAccountReq;
import com.xlf.trade.vo.request.TradeRechargeReq;

public interface TradeAccountService {
    /**
     * create account
     *
     * @param req
     */
    void createAccount(CreateTradeAccountReq req);

    void recharge(TradeRechargeReq req);
}
