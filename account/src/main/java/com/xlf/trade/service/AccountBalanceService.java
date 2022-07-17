package com.xlf.trade.service;

import com.xlf.trade.vo.request.*;

public interface AccountBalanceService {
    void recharge(RechargeReq req);

    void withdraw(WithdrawReq req);

    void transaction(TransactionReq req);

    void frozen(FrozenReq req);

    void unfrozen(UnfrozenReq req);
}
