package com.xlf.trade.web;

import com.xlf.common.response.ApiResult;
import com.xlf.trade.service.TradeAccountService;
import com.xlf.trade.vo.request.CreateTradeAccountReq;
import com.xlf.trade.vo.request.DeleteTradeAccountReq;
import com.xlf.trade.vo.response.CreateAccountRsp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/trade/account")
@Slf4j
public class TradeAccountController {

    @Resource
    private TradeAccountService tradeAccountService;

    @PostMapping("/create")
    public ApiResult<CreateAccountRsp> createAccount(@RequestBody @Valid CreateTradeAccountReq req) {
        tradeAccountService.createAccount(req);
        return ApiResult.success();
    }

    @PostMapping("/delete")
    public ApiResult<Object> deleteAccount(@RequestBody @Valid DeleteTradeAccountReq req) {

        return ApiResult.success();
    }
}
