package com.xlf.account.service.impl;

import com.xlf.account.entity.AccountFlowDo;
import com.xlf.account.entity.AccountInfoDo;
import com.xlf.account.enums.AccountFlowOperateTypeEnums;
import com.xlf.account.service.AccountFlowService;
import com.xlf.account.vo.request.DeleteAccountReq;
import com.xlf.account.vo.request.RechargeReq;
import com.xlf.account.vo.request.TransactionReq;
import com.xlf.account.vo.request.WithdrawReq;
import com.xlf.common.util.SnowflakeUtil;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountFlowServiceImpl implements AccountFlowService {
    @Override
    public AccountFlowDo recharge(AccountInfoDo accountInfoDo, RechargeReq req) {
        AccountFlowDo accountFlow = new AccountFlowDo();
        accountFlow.setFlowId(SnowflakeUtil.nextId());
        accountFlow.setAccountId(accountInfoDo.getId());
        accountFlow.setUserId(accountInfoDo.getUserId());
        accountFlow.setAccountType(accountInfoDo.getAccountType());
        accountFlow.setTransId(req.getTransId());
        accountFlow.setOperateType(AccountFlowOperateTypeEnums.RECHARGE.getCode());
        accountFlow.setAmount(req.getAmount());
        accountFlow.setCurrencyType(accountInfoDo.getCurrencyType());
        accountFlow.setBalance(accountInfoDo.getBalance() + req.getAmount());
        accountFlow.setFrozenBalance(accountFlow.getFrozenBalance());
        return accountFlow;
    }

    @Override
    public AccountFlowDo withdraw(AccountInfoDo accountInfoDo, WithdrawReq req) {
        AccountFlowDo accountFlow = new AccountFlowDo();
        accountFlow.setFlowId(SnowflakeUtil.nextId());
        accountFlow.setAccountId(accountInfoDo.getId());
        accountFlow.setUserId(accountInfoDo.getUserId());
        accountFlow.setAccountType(accountInfoDo.getAccountType());
        accountFlow.setTransId(req.getTransId());
        accountFlow.setOperateType(AccountFlowOperateTypeEnums.WITHDRAW.getCode());
        accountFlow.setAmount(req.getAmount());
        accountFlow.setCurrencyType(accountInfoDo.getCurrencyType());
        accountFlow.setBalance(accountInfoDo.getBalance() - req.getAmount());
        accountFlow.setFrozenBalance(accountFlow.getFrozenBalance());
        return accountFlow;
    }

    @Override
    public List<AccountFlowDo> transaction(AccountInfoDo fromAccount, AccountInfoDo toAccount, TransactionReq req) {
        List<AccountFlowDo> retList = new ArrayList<>(2);

        AccountFlowDo accountFlow = new AccountFlowDo();
        accountFlow.setFlowId(SnowflakeUtil.nextId());
        accountFlow.setAccountId(fromAccount.getId());
        accountFlow.setUserId(fromAccount.getUserId());
        accountFlow.setAccountType(fromAccount.getAccountType());
        accountFlow.setToAccountId(toAccount.getId());
        accountFlow.setToUserId(toAccount.getUserId());
        accountFlow.setToAccountType(toAccount.getAccountType());
        accountFlow.setTransId(req.getTransId());
        accountFlow.setOperateType(AccountFlowOperateTypeEnums.TRANSACTION_OUT.getCode());
        accountFlow.setAmount(req.getAmount());
        accountFlow.setCurrencyType(fromAccount.getCurrencyType());
        accountFlow.setBalance(fromAccount.getBalance() - req.getAmount());
        accountFlow.setFrozenBalance(accountFlow.getFrozenBalance());
        retList.add(accountFlow);

        accountFlow = new AccountFlowDo();
        accountFlow.setFlowId(SnowflakeUtil.nextId());
        accountFlow.setAccountId(toAccount.getId());
        accountFlow.setUserId(toAccount.getUserId());
        accountFlow.setAccountType(toAccount.getAccountType());
        accountFlow.setToAccountId(fromAccount.getId());
        accountFlow.setToUserId(fromAccount.getUserId());
        accountFlow.setToAccountType(fromAccount.getAccountType());
        accountFlow.setTransId(req.getTransId());
        accountFlow.setOperateType(AccountFlowOperateTypeEnums.TRANSACTION_IN.getCode());
        accountFlow.setAmount(req.getAmount());
        accountFlow.setCurrencyType(toAccount.getCurrencyType());
        accountFlow.setBalance(toAccount.getBalance() + req.getAmount());
        accountFlow.setFrozenBalance(accountFlow.getFrozenBalance());
        retList.add(accountFlow);

        return retList;
    }

    @Override
    public AccountFlowDo createDeleteAccount(AccountInfoDo accountInfoDo, String tansId, AccountFlowOperateTypeEnums operateTypeEnums) {
        AccountFlowDo accountFlow = new AccountFlowDo();
        accountFlow.setFlowId(SnowflakeUtil.nextId());
        accountFlow.setAccountId(accountInfoDo.getId());
        accountFlow.setUserId(accountInfoDo.getUserId());
        accountFlow.setAccountType(accountInfoDo.getAccountType());
        accountFlow.setTransId(tansId);
        accountFlow.setOperateType(operateTypeEnums.getCode());
        accountFlow.setCurrencyType(accountInfoDo.getCurrencyType());
        accountFlow.setBalance(accountInfoDo.getBalance());
        accountFlow.setFrozenBalance(accountFlow.getFrozenBalance());
        return accountFlow;
    }
}
