package com.xlf.trade.remote.account;

import com.xlf.account.common.request.*;
import com.xlf.account.common.response.CreateAccountRsp;
import com.xlf.common.enums.AccountCreateTypeEnums;
import com.xlf.common.enums.AccountTypeEnums;
import com.xlf.common.response.ApiResult;
import com.xlf.trade.entity.PayOrderDo;
import com.xlf.trade.enums.PayOrderStatusEnums;
import com.xlf.trade.repository.PayOrderRepository;
import com.xlf.trade.vo.request.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountRemoteService {
    @Resource
    private PayOrderRepository payOrderRepository;
    @Resource
    private AccountServiceFeign accountServiceFeign;

    @Value("${spring.application.name}")
    private String appId;

    public CreateAccountRsp createAccount(CreateTradeAccountReq req, PayOrderDo payOrderDo, AccountTypeEnums accountTypeEnums) {
        if (payOrderDo.getStatus() != PayOrderStatusEnums.CALLING_ACCOUNT.getCode()) {
            return null;
        }
        CreateAccountReq accountReq = new CreateAccountReq();
        accountReq.setAppId(appId);
        accountReq.setOrderTime(payOrderDo.getOrderTime());
        accountReq.setTransId(payOrderDo.getTransId());
        accountReq.setUserId(req.getUserId());
        accountReq.setAccountType(accountTypeEnums.getCode());
        accountReq.setAccountName(req.getAccountName());
        accountReq.setUserName(req.getUserName());
        accountReq.setCreateBy(req.getUserId());
        accountReq.setCreateType(AccountCreateTypeEnums.USER.getCode());
        accountReq.setCurrencyType(req.getCurrencyType());
        ApiResult<CreateAccountRsp> apiResult = accountServiceFeign.createAccount(accountReq);
        afterCallingAccount(payOrderDo, apiResult);
        return apiResult.getData();
    }

    public boolean recharge(TradeRechargeReq req, PayOrderDo payOrderDo, AccountTypeEnums accountTypeEnums) {
        if (payOrderDo.getStatus() != PayOrderStatusEnums.CALLING_ACCOUNT.getCode()) {
            return false;
        }
        RechargeReq accountReq = new RechargeReq();
        accountReq.setAppId(appId);
        accountReq.setOrderTime(payOrderDo.getOrderTime());
        accountReq.setTransId(payOrderDo.getTransId());
        accountReq.setUserId(req.getUserId());
        accountReq.setAccountType(accountTypeEnums.getCode());
        accountReq.setAmount(req.getAmount());
        ApiResult<Object> apiResult = accountServiceFeign.recharge(accountReq);
        return afterCallingAccount(payOrderDo, apiResult);
    }

    public boolean withdraw(TradeWithdrawReq req, PayOrderDo payOrderDo, AccountTypeEnums accountTypeEnums) {
        if (payOrderDo.getStatus() != PayOrderStatusEnums.CALLING_ACCOUNT.getCode()) {
            return false;
        }
        WithdrawReq accountReq = new WithdrawReq();
        accountReq.setAppId(appId);
        accountReq.setOrderTime(payOrderDo.getOrderTime());
        accountReq.setTransId(payOrderDo.getTransId());
        accountReq.setUserId(req.getUserId());
        accountReq.setAccountType(accountTypeEnums.getCode());
        accountReq.setAmount(req.getAmount());
        ApiResult<Object> apiResult = accountServiceFeign.withdraw(accountReq);
        return afterCallingAccount(payOrderDo, apiResult);
    }

    public boolean transaction(TradeTransactionReq req, PayOrderDo payOrderDo, AccountTypeEnums accountTypeEnums) {
        if (payOrderDo.getStatus() != PayOrderStatusEnums.CALLING_ACCOUNT.getCode()) {
            return false;
        }
        TransactionReq accountReq = new TransactionReq();
        accountReq.setAppId(appId);
        accountReq.setOrderTime(payOrderDo.getOrderTime());
        accountReq.setTransId(payOrderDo.getTransId());
        accountReq.setUserId(req.getUserId());
        accountReq.setAccountType(accountTypeEnums.getCode());
        accountReq.setToUserId(req.getToUserId());
        accountReq.setToAccountType(accountTypeEnums.getCode());
        accountReq.setAmount(req.getAmount());
        ApiResult<Object> apiResult = accountServiceFeign.transaction(accountReq);
        return afterCallingAccount(payOrderDo, apiResult);
    }

    public boolean frozen(TradeFrozenReq req, PayOrderDo payOrderDo, AccountTypeEnums accountTypeEnums) {
        if (payOrderDo.getStatus() != PayOrderStatusEnums.CALLING_ACCOUNT.getCode()) {
            return false;
        }
        FrozenReq accountReq = new FrozenReq();
        accountReq.setAppId(appId);
        accountReq.setOrderTime(payOrderDo.getOrderTime());
        accountReq.setTransId(payOrderDo.getTransId());
        accountReq.setUserId(req.getUserId());
        accountReq.setAccountType(accountTypeEnums.getCode());
        accountReq.setAmount(req.getAmount());
        ApiResult<Object> apiResult = accountServiceFeign.frozen(accountReq);
        return afterCallingAccount(payOrderDo, apiResult);
    }

    public boolean unfrozen(TradeUnfrozenReq req, PayOrderDo payOrderDo, AccountTypeEnums accountTypeEnums) {
        if (payOrderDo.getStatus() != PayOrderStatusEnums.CALLING_ACCOUNT.getCode()) {
            return false;
        }
        UnfrozenReq accountReq = new UnfrozenReq();
        accountReq.setAppId(appId);
        accountReq.setOrderTime(payOrderDo.getOrderTime());
        accountReq.setTransId(payOrderDo.getTransId());
        accountReq.setUserId(req.getUserId());
        accountReq.setAccountType(accountTypeEnums.getCode());
        accountReq.setAmount(req.getAmount());
        ApiResult<Object> apiResult = accountServiceFeign.unfrozen(accountReq);
        return afterCallingAccount(payOrderDo, apiResult);
    }

    protected boolean afterCallingAccount(PayOrderDo payOrderDo, ApiResult<?> apiResult) {
        int toStatus;
        String remark = null;
        if (apiResult.isSuccess()) {
            toStatus = PayOrderStatusEnums.SUCCESS.getCode();
        } else {
            toStatus = PayOrderStatusEnums.CALLING_ACCOUNT_FAIL.getCode();
            remark = apiResult.getMsg();
        }
        return payOrderRepository.afterCalling(payOrderDo.getOrderId(),
                null,
                PayOrderStatusEnums.CALLING_ACCOUNT.getCode(),
                toStatus,
                remark);
    }



}
