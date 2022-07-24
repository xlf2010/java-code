package com.xlf.account.service;

import com.xlf.account.common.request.*;

public interface AccountBalanceService {
    void recharge(RechargeReq req);

    void withdraw(WithdrawReq req);

    void transaction(TransactionReq req);

    void frozen(FrozenReq req);

    void unfrozen(UnfrozenReq req);
}
