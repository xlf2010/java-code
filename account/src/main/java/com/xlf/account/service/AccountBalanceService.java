package com.xlf.account.service;

import com.xlf.account.entity.AccountInfoDo;
import com.xlf.account.vo.request.*;
import com.xlf.common.exception.ErrorCodeEnum;
import org.springframework.transaction.annotation.Transactional;

public interface AccountBalanceService {
    void recharge(RechargeReq req);

    void withdraw(WithdrawReq req);

    void transaction(TransactionReq req);

    void frozen(FrozenReq req);

    void unfrozen(UnfrozenReq req);
}
