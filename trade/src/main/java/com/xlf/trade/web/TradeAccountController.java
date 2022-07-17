package com.xlf.trade.web;

import com.xlf.common.response.ApiResult;
import com.xlf.trade.service.TradeAccountService;
import com.xlf.trade.vo.request.CreateAccountReq;
import com.xlf.trade.vo.request.DeleteAccountReq;
import com.xlf.trade.vo.response.CreateAccountRsp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class TradeAccountController {

    @Resource
    private TradeAccountService tradeAccountService;

    @PostMapping("/create")
    public ApiResult<CreateAccountRsp> createAccount(@RequestBody @Valid CreateAccountReq req) {
        return ApiResult.success();
    }

    @PostMapping("/delete")
    public ApiResult<Object> deleteAccount(@RequestBody @Valid DeleteAccountReq req) {

        return ApiResult.success();
    }
}
