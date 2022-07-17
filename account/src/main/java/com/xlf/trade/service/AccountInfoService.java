package com.xlf.trade.service;

import com.xlf.trade.entity.AccountInfoDo;
import com.xlf.trade.vo.request.CreateAccountReq;
import com.xlf.trade.vo.request.DeleteAccountReq;

public interface AccountInfoService {
    Long createAccount(CreateAccountReq req);

    AccountInfoDo deleteAccount(DeleteAccountReq req);
}
