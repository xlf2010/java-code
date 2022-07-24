package com.xlf.trade.remote.account;

import com.xlf.account.common.request.*;
import com.xlf.account.common.response.CreateAccountRsp;
import com.xlf.account.common.response.QueryAccountInfoRsp;
import com.xlf.common.response.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "xlf-account")
public interface AccountServiceFeign {
    @GetMapping(value = "/account/query/account_info")
    ApiResult<List<QueryAccountInfoRsp>> queryAccountInfo(@RequestParam("userId") String userId,
                                                          @RequestParam(value = "accountType", required = false) Integer accountType);

    @PostMapping(value = "/account/create")
    ApiResult<CreateAccountRsp> createAccount(CreateAccountReq req);

    @PostMapping(value = "/account/recharge")
    ApiResult<Object> recharge(RechargeReq req);

    @PostMapping(value = "/account/withdraw")
    ApiResult<Object> withdraw(WithdrawReq req);

    @PostMapping(value = "/account/transaction")
    ApiResult<Object> transaction(TransactionReq req);

    @PostMapping(value = "/account/frozen")
    ApiResult<Object> frozen(FrozenReq req);

    @PostMapping(value = "/account/unfrozen")
    ApiResult<Object> unfrozen(UnfrozenReq req);
}
