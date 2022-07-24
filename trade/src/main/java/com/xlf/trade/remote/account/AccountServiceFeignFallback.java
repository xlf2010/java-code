package com.xlf.trade.remote.account;

import com.xlf.account.common.request.*;
import com.xlf.account.common.response.CreateAccountRsp;
import com.xlf.account.common.response.QueryAccountInfoRsp;
import com.xlf.common.response.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
//@Component
public class AccountServiceFeignFallback implements AccountServiceFeign {
    @Override
    public ApiResult<List<QueryAccountInfoRsp>> queryAccountInfo(String userId, Integer accountType) {
        log.error("query account fallback");
        return ApiResult.fail();
    }

    @Override
    public ApiResult<CreateAccountRsp> createAccount(CreateAccountReq req) {
        return ApiResult.fail();
    }

    @Override
    public ApiResult<Object> recharge(RechargeReq req) {
        return ApiResult.fail();
    }

    @Override
    public ApiResult<Object> withdraw(WithdrawReq req) {
        return ApiResult.fail();
    }

    @Override
    public ApiResult<Object> transaction(TransactionReq req) {
        return ApiResult.fail();
    }

    @Override
    public ApiResult<Object> frozen(FrozenReq req) {
        return ApiResult.fail();
    }

    @Override
    public ApiResult<Object> unfrozen(UnfrozenReq req) {
        return ApiResult.fail();
    }
}
