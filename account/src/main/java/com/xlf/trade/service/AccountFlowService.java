package com.xlf.trade.service;

import com.xlf.trade.entity.AccountFlowDo;
import com.xlf.trade.entity.AccountInfoDo;
import com.xlf.trade.enums.AccountFlowOperateTypeEnums;
import com.xlf.trade.vo.request.*;

import java.util.List;

public interface AccountFlowService {
    AccountFlowDo recharge(AccountInfoDo accountInfoDo, RechargeReq req);

    AccountFlowDo withdraw(AccountInfoDo accountInfoDo, WithdrawReq req);

    List<AccountFlowDo> transaction(AccountInfoDo fromAccount, AccountInfoDo toAccount, TransactionReq amount);

    AccountFlowDo createDeleteAccount(AccountInfoDo accountInfoDo, String tansId, AccountFlowOperateTypeEnums operateTypeEnums);

    AccountFlowDo frozen(AccountInfoDo accountInfoDo, FrozenReq req);

    AccountFlowDo unfrozen(AccountInfoDo accountInfoDo, UnfrozenReq req);
}
