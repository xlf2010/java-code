package com.xlf.account.service;

import com.xlf.account.common.request.*;
import com.xlf.account.common.response.CreateAccountRsp;

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
