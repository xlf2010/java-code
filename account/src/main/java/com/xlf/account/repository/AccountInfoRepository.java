package com.xlf.account.repository;

import com.xlf.account.entity.AccountInfoDo;
import com.xlf.account.entity.AccountInfoDoExample;
import com.xlf.account.enums.AccountStatusEnums;
import com.xlf.account.repository.mapper.AccountInfoExtMapper;
import com.xlf.account.repository.mapper.AccountInfoMapper;
import com.xlf.account.vo.request.TransactionReq;
import com.xlf.common.exception.BusinessException;
import com.xlf.common.exception.ErrorCodeEnum;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AccountInfoRepository {
    @Resource
    private AccountInfoMapper accountInfoMapper;
    @Resource
    private AccountInfoExtMapper accountInfoExtMapper;

    public void createAccount(AccountInfoDo accountInfoDo) {
        accountInfoMapper.insertSelective(accountInfoDo);
    }

    public AccountInfoDo queryByUserIdAccountTypeForUpdate(String userId, Integer accountType) {
        AccountInfoDoExample example = new AccountInfoDoExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andAccountTypeEqualTo(accountType)
                .andStatusEqualTo(AccountStatusEnums.VALID.getCode());
        return accountInfoExtMapper.queryAccountInfoForUpdate(userId, accountType);
    }

    public boolean recharge(Long accountId, Long amount) {
        return accountInfoExtMapper.incrBalance(accountId, amount) == 1;
    }

    public boolean withdraw(Long accountId, Long amount) {
        return accountInfoExtMapper.incrBalance(accountId, -amount) == 1;
    }

    public void transaction(AccountInfoDo fromAccount, AccountInfoDo toAccount, Long amount) {
        int rowAffected = accountInfoExtMapper.incrBalance(fromAccount.getId(), -amount);
        if (rowAffected != 1) {
            throw ErrorCodeEnum.TRANSACTION_ERROR.newException();
        }
        rowAffected = accountInfoExtMapper.incrBalance(toAccount.getId(), amount);
        if (rowAffected != 1) {
            throw ErrorCodeEnum.TRANSACTION_ERROR.newException();
        }
    }
}
