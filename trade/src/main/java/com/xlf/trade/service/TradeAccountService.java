package com.xlf.trade.service;

import com.xlf.trade.vo.request.*;

public interface TradeAccountService {
    /**
     * create account
     *
     * @param req
     */
    void createAccount(CreateTradeAccountReq req);

    void recharge(TradeRechargeReq req);

    void withdraw(TradeWithdrawReq req);

    void transaction(TradeTransactionReq req);

    void frozen(TradeFrozenReq req);

    void unfrozen(TradeUnfrozenReq req);
}
