package com.xlf.trade.web;

import com.xlf.common.response.ApiResult;
import com.xlf.trade.service.TradeAccountService;
import com.xlf.trade.vo.request.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class TradeController {

    @Resource
    private TradeAccountService tradeAccountService;


    @PostMapping("/recharge")
    public ApiResult<Object> recharge(@RequestBody @Valid RechargeReq req) {
        return ApiResult.success();
    }

    @PostMapping("/withdraw")
    public ApiResult<Object> withdraw(@RequestBody @Valid WithdrawReq req) {
        return ApiResult.success();
    }

    @PostMapping("/transaction")
    public ApiResult<Object> transaction(@RequestBody @Valid TransactionReq req) {
        return ApiResult.success();
    }

    @PostMapping("/frozen")
    public ApiResult<Object> frozen(@RequestBody @Valid FrozenReq req) {
        return ApiResult.success();
    }

    @PostMapping("/unfrozen")
    public ApiResult<Object> unfrozen(@RequestBody @Valid UnfrozenReq req) {
        return ApiResult.success();
    }

}
