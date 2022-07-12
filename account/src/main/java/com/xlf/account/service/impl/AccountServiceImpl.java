package com.xlf.account.service.impl;

import com.xlf.account.entity.AccountInfoDo;
import com.xlf.account.repository.AccountInfoRepository;
import com.xlf.account.service.AccountService;
import com.xlf.account.vo.request.CreateAccountReq;
import com.xlf.account.vo.response.CreateAccountRsp;
import com.xlf.common.util.SnowflakeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountInfoRepository accountInfoRepository;

    @Override
    public CreateAccountRsp createAccount(CreateAccountReq req) {
        AccountInfoDo accountInfo = new AccountInfoDo();
        accountInfo.setId(SnowflakeUtil.nextId());
        accountInfo.setAccountName(req.getAccountName());
        accountInfo.setAccountType(req.getAccountType());
        accountInfo.setUserId(req.getUserId());
        accountInfo.setUserName(req.getUserName());
        if (Objects.nonNull(req.getCreateType())){
            accountInfo.setAccountType(req.getAccountType());
        }
        if (StringUtils.isNotBlank(req.getCreateBy())){
            accountInfo.setCreateBy(req.getCreateBy());
            accountInfo.setUpdateBy(req.getCreateBy());
        }
        accountInfoRepository.createAccount(accountInfo);

        CreateAccountRsp rsp = new CreateAccountRsp();
        rsp.setAccountId(accountInfo.getId().toString());
        return rsp;
    }
}
