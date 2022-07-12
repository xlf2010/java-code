package com.xlf.account.service;

import com.xlf.account.vo.request.CreateAccountReq;
import com.xlf.account.vo.response.CreateAccountRsp;

public interface AccountService {
    CreateAccountRsp createAccount(CreateAccountReq req);

}
