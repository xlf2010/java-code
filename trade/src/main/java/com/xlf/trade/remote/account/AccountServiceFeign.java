package com.xlf.trade.remote.account;

import com.xlf.account.common.request.CreateAccountReq;
import com.xlf.account.common.request.RechargeReq;
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
@FeignClient(name = "xlf-account", fallback = AccountServiceFeignFallback.class)
public interface AccountServiceFeign {
    @GetMapping(value = "/account/query/account_info")
    ApiResult<List<QueryAccountInfoRsp>> queryAccountInfo(@RequestParam("userId") String userId,
                                                          @RequestParam(value = "accountType", required = false) Integer accountType);

    @PostMapping(value = "/account/create")
    ApiResult<CreateAccountRsp> createAccount(CreateAccountReq req);

    @PostMapping(value = "/account/recharge")
    ApiResult<Object> recharge(RechargeReq req);

}
