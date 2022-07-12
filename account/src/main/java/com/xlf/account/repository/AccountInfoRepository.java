package com.xlf.account.repository;

import com.xlf.account.entity.AccountInfoDo;
import com.xlf.account.repository.mapper.AccountInfoMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AccountInfoRepository {
    @Resource
    private AccountInfoMapper accountInfoMapper;

    public void createAccount(AccountInfoDo accountInfoDo) {
        accountInfoMapper.insertSelective(accountInfoDo);
    }
}
