package com.xlf.account.service;

import com.xlf.account.vo.request.RechargeReq;
import com.xlf.account.vo.request.TransactionReq;
import com.xlf.account.vo.request.WithdrawReq;

public interface AccountBalanceService {
    void recharge(RechargeReq req);

    void withdraw(WithdrawReq req);

    void transaction(TransactionReq req);
}
