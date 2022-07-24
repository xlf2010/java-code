package com.xlf.account.service.impl;

import com.xlf.account.common.request.*;
import com.xlf.account.entity.AccountFlowDo;
import com.xlf.account.entity.AccountInfoDo;
import com.xlf.account.enums.AccountFlowOperateTypeEnums;
import com.xlf.account.service.AccountFlowService;
import com.xlf.common.util.SnowflakeUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccountFlowServiceImpl implements AccountFlowService {
    @Override
    public AccountFlowDo recharge(AccountInfoDo accountInfoDo, RechargeReq req) {
        AccountFlowDo accountFlow = init(accountInfoDo);
        accountFlow.setTransId(req.getTransId());
        accountFlow.setOperateType(AccountFlowOperateTypeEnums.RECHARGE.getCode());
        accountFlow.setAmount(req.getAmount());
        accountFlow.setCurrencyType(accountInfoDo.getCurrencyType());
        accountFlow.setBalance(accountInfoDo.getBalance() + req.getAmount());
        accountFlow.setFrozenBalance(accountInfoDo.getFrozenBalance());
        accountFlow.setAppId(req.getAppId());
        accountFlow.setOrderTime(req.getOrderTime());
        return accountFlow;
    }

    @Override
    public AccountFlowDo withdraw(AccountInfoDo accountInfoDo, WithdrawReq req) {
        AccountFlowDo accountFlow = init(accountInfoDo);
        accountFlow.setTransId(req.getTransId());
        accountFlow.setOperateType(AccountFlowOperateTypeEnums.WITHDRAW.getCode());
        accountFlow.setAmount(req.getAmount());
        accountFlow.setCurrencyType(accountInfoDo.getCurrencyType());
        accountFlow.setBalance(accountInfoDo.getBalance() - req.getAmount());
        accountFlow.setFrozenBalance(accountInfoDo.getFrozenBalance());
        accountFlow.setAppId(req.getAppId());
        accountFlow.setOrderTime(req.getOrderTime());
        return accountFlow;
    }

    @Override
    public List<AccountFlowDo> transaction(AccountInfoDo fromAccount, AccountInfoDo toAccount, TransactionReq req) {
        List<AccountFlowDo> retList = new ArrayList<>(2);

        AccountFlowDo accountFlow = init(fromAccount);
        accountFlow.setToAccountId(toAccount.getId());
        accountFlow.setToUserId(toAccount.getUserId());
        accountFlow.setToAccountType(toAccount.getAccountType());
        accountFlow.setTransId(req.getTransId());
        accountFlow.setOperateType(AccountFlowOperateTypeEnums.TRANSACTION_OUT.getCode());
        accountFlow.setAmount(req.getAmount());
        accountFlow.setCurrencyType(fromAccount.getCurrencyType());
        accountFlow.setBalance(fromAccount.getBalance() - req.getAmount());
        accountFlow.setFrozenBalance(fromAccount.getFrozenBalance());
        accountFlow.setAppId(req.getAppId());
        accountFlow.setOrderTime(req.getOrderTime());
        retList.add(accountFlow);

        accountFlow = init(toAccount);
        accountFlow.setToAccountId(fromAccount.getId());
        accountFlow.setToUserId(fromAccount.getUserId());
        accountFlow.setToAccountType(fromAccount.getAccountType());
        accountFlow.setTransId(req.getTransId());
        accountFlow.setOperateType(AccountFlowOperateTypeEnums.TRANSACTION_IN.getCode());
        accountFlow.setAmount(req.getAmount());
        accountFlow.setCurrencyType(toAccount.getCurrencyType());
        accountFlow.setBalance(toAccount.getBalance() + req.getAmount());
        accountFlow.setFrozenBalance(toAccount.getFrozenBalance());
        accountFlow.setAppId(req.getAppId());
        accountFlow.setOrderTime(req.getOrderTime());
        retList.add(accountFlow);

        return retList;
    }

    @Override
    public AccountFlowDo createDeleteAccount(AccountInfoDo accountInfoDo,
                                             String appId,
                                             Date orderTime,
                                             String tansId,
                                             AccountFlowOperateTypeEnums operateTypeEnums) {
        AccountFlowDo accountFlow = init(accountInfoDo);
        accountFlow.setTransId(tansId);
        accountFlow.setOperateType(operateTypeEnums.getCode());
        accountFlow.setCurrencyType(accountInfoDo.getCurrencyType());
        accountFlow.setBalance(accountInfoDo.getBalance());
        accountFlow.setFrozenBalance(accountInfoDo.getFrozenBalance());
        accountFlow.setAppId(appId);
        accountFlow.setOrderTime(orderTime);
        return accountFlow;
    }

    @Override
    public AccountFlowDo frozen(AccountInfoDo accountInfoDo, FrozenReq req) {
        AccountFlowDo accountFlow = init(accountInfoDo);
        accountFlow.setTransId(req.getTransId());
        accountFlow.setOperateType(AccountFlowOperateTypeEnums.FROZEN.getCode());
        accountFlow.setCurrencyType(accountInfoDo.getCurrencyType());
        accountFlow.setAmount(req.getAmount());
        accountFlow.setBalance(accountInfoDo.getBalance() - req.getAmount());
        accountFlow.setFrozenBalance(accountInfoDo.getFrozenBalance() + req.getAmount());
        accountFlow.setAppId(req.getAppId());
        accountFlow.setOrderTime(req.getOrderTime());
        return accountFlow;
    }

    @Override
    public AccountFlowDo unfrozen(AccountInfoDo accountInfoDo, UnfrozenReq req) {
        AccountFlowDo accountFlow = init(accountInfoDo);
        accountFlow.setTransId(req.getTransId());
        accountFlow.setOperateType(AccountFlowOperateTypeEnums.UNFROZEN.getCode());
        accountFlow.setCurrencyType(accountInfoDo.getCurrencyType());
        accountFlow.setAmount(req.getAmount());
        accountFlow.setBalance(accountInfoDo.getBalance() + req.getAmount());
        accountFlow.setFrozenBalance(accountInfoDo.getFrozenBalance() - req.getAmount());
        accountFlow.setAppId(req.getAppId());
        accountFlow.setOrderTime(req.getOrderTime());
        return accountFlow;
    }

    private AccountFlowDo init(AccountInfoDo accountInfoDo) {
        AccountFlowDo accountFlow = new AccountFlowDo();
        accountFlow.setFlowId(SnowflakeUtil.nextId());
        accountFlow.setAccountId(accountInfoDo.getId());
        accountFlow.setUserId(accountInfoDo.getUserId());
        accountFlow.setAccountType(accountInfoDo.getAccountType());
        return accountFlow;
    }
}
