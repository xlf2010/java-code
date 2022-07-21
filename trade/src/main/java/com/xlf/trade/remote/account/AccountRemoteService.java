package com.xlf.trade.remote.account;

import com.xlf.account.common.request.CreateAccountReq;
import com.xlf.account.common.request.RechargeReq;
import com.xlf.account.common.response.CreateAccountRsp;
import com.xlf.common.enums.AccountCreateTypeEnums;
import com.xlf.common.enums.AccountTypeEnums;
import com.xlf.common.response.ApiResult;
import com.xlf.trade.entity.PayOrderDo;
import com.xlf.trade.enums.PayOrderStatusEnums;
import com.xlf.trade.repository.PayOrderRepository;
import com.xlf.trade.vo.request.CreateTradeAccountReq;
import com.xlf.trade.vo.request.TradeRechargeReq;
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

    public CreateAccountRsp createAccount(CreateTradeAccountReq createTradeAccountReq, PayOrderDo payOrderDo, AccountTypeEnums accountTypeEnums) {
        if (payOrderDo.getStatus() != PayOrderStatusEnums.CALLING_ACCOUNT.getCode()) {
            return null;
        }
        CreateAccountReq req = new CreateAccountReq();
        req.setAppId(appId);
        req.setOrderTime(payOrderDo.getOrderTime());
        req.setTransId(payOrderDo.getTransId());
        req.setUserId(createTradeAccountReq.getUserId());
        req.setAccountType(accountTypeEnums.getCode());
        req.setAccountName(createTradeAccountReq.getAccountName());
        req.setUserName(createTradeAccountReq.getUserName());
        req.setCreateBy(createTradeAccountReq.getUserId());
        req.setCreateType(AccountCreateTypeEnums.USER.getCode());
        req.setCurrencyType(createTradeAccountReq.getCurrencyType());
        ApiResult<CreateAccountRsp> apiResult = accountServiceFeign.createAccount(req);
        afterCallingAccount(payOrderDo, apiResult);
        return apiResult.getData();
    }

    public boolean recharge(TradeRechargeReq tradeRechargeReq, PayOrderDo payOrderDo, AccountTypeEnums accountTypeEnums) {
        if (payOrderDo.getStatus() != PayOrderStatusEnums.CALLING_ACCOUNT.getCode()) {
            return false;
        }
        RechargeReq req = new RechargeReq();
        req.setAppId(appId);
        req.setOrderTime(payOrderDo.getOrderTime());
        req.setTransId(payOrderDo.getTransId());
        req.setUserId(tradeRechargeReq.getUserId());
        req.setAccountType(accountTypeEnums.getCode());
        req.setAmount(tradeRechargeReq.getAmount());
        ApiResult<Object> apiResult = accountServiceFeign.recharge(req);
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
