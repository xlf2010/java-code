package com.xlf.account.service;

import com.xlf.account.entity.AccountFlowDo;
import com.xlf.account.entity.AccountInfoDo;
import com.xlf.account.vo.request.RechargeReq;
import com.xlf.account.vo.request.TransactionReq;
import com.xlf.account.vo.request.WithdrawReq;

import java.util.List;

public interface AccountFlowService {
    AccountFlowDo recharge(AccountInfoDo accountInfoDo, RechargeReq req);

    AccountFlowDo withdraw(AccountInfoDo accountInfoDo, WithdrawReq req);

    List<AccountFlowDo> transaction(AccountInfoDo fromAccount, AccountInfoDo toAccount, TransactionReq amount);
}
