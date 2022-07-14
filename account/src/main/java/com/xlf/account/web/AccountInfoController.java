package com.xlf.account.web;

import com.xlf.account.service.AccountService;
import com.xlf.account.vo.request.*;
import com.xlf.account.vo.response.CreateAccountRsp;
import com.xlf.common.response.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountInfoController {

    @Resource
    private AccountService accountService;

    @PostMapping("/create")
    public ApiResult<CreateAccountRsp> createAccount(@RequestBody @Valid CreateAccountReq req) {
        return ApiResult.success(accountService.createAccount(req));
    }

    @PostMapping("/recharge")
    public ApiResult<Object> recharge(@RequestBody @Valid RechargeReq req) {
        accountService.recharge(req);
        return ApiResult.success();
    }

    @PostMapping("/withdraw")
    public ApiResult<Object> withdraw(@RequestBody @Valid WithdrawReq req) {
        accountService.withdraw(req);
        return ApiResult.success();
    }

    @PostMapping("/transaction")
    public ApiResult<Object> transaction(@RequestBody @Valid TransactionReq req) {
        accountService.transaction(req);
        return ApiResult.success();
    }

    @PostMapping("/delete")
    public ApiResult<Object> deleteAccount(@RequestBody @Valid DeleteAccountReq req) {
        accountService.deleteAccount(req);
        return ApiResult.success();
    }
}
