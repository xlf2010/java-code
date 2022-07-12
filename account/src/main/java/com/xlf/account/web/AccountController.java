package com.xlf.account.web;

import com.xlf.account.vo.request.CreateAccountReq;
import com.xlf.account.vo.response.CreateAccountRsp;
import com.xlf.common.response.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    @PostMapping("/create")
    public ApiResult<CreateAccountRsp> createAccount(@RequestBody @Valid CreateAccountReq req) {

        return ApiResult.success();
    }
}
