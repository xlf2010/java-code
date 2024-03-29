package com.xlf.account.service;

import com.xlf.account.common.request.CreateAccountReq;
import com.xlf.account.common.request.DeleteAccountReq;
import com.xlf.account.entity.AccountInfoDo;

public interface AccountInfoService {
    Long createAccount(CreateAccountReq req);

    AccountInfoDo deleteAccount(DeleteAccountReq req);
}
