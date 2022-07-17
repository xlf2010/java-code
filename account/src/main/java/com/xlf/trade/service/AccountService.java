package com.xlf.trade.service;

import com.xlf.trade.vo.request.*;
import com.xlf.trade.vo.response.CreateAccountRsp;

public interface AccountService {
    CreateAccountRsp createAccount(CreateAccountReq req);

    void recharge(RechargeReq req);

    void withdraw(WithdrawReq req);

    void transaction(TransactionReq req);

    void frozen(FrozenReq req);

    void unfrozen(UnfrozenReq req);

    void deleteAccount(DeleteAccountReq req);

    void checkAndBackupAccountFlow();
}
