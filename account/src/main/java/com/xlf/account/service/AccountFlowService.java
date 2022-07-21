package com.xlf.account.service;

import com.xlf.account.entity.AccountFlowDo;
import com.xlf.account.entity.AccountInfoDo;
import com.xlf.account.enums.AccountFlowOperateTypeEnums;
import com.xlf.account.common.request.*;

import java.util.Date;
import java.util.List;

public interface AccountFlowService {
    AccountFlowDo recharge(AccountInfoDo accountInfoDo, RechargeReq req);

    AccountFlowDo withdraw(AccountInfoDo accountInfoDo, WithdrawReq req);

    List<AccountFlowDo> transaction(AccountInfoDo fromAccount, AccountInfoDo toAccount, TransactionReq amount);

    AccountFlowDo createDeleteAccount(AccountInfoDo accountInfoDo,
                                      String appId,
                                      Date orderTime,
                                      String tansId,
                                      AccountFlowOperateTypeEnums operateTypeEnums);

    AccountFlowDo frozen(AccountInfoDo accountInfoDo, FrozenReq req);

    AccountFlowDo unfrozen(AccountInfoDo accountInfoDo, UnfrozenReq req);
}
