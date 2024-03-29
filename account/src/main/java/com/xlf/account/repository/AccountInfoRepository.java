package com.xlf.account.repository;

import com.google.common.collect.Lists;
import com.xlf.account.entity.AccountInfoBakDo;
import com.xlf.account.entity.AccountInfoBakDoExample;
import com.xlf.account.entity.AccountInfoDo;
import com.xlf.account.entity.AccountInfoDoExample;
import com.xlf.account.enums.AccountStatusEnums;
import com.xlf.account.enums.BackupFlowStatusEnums;
import com.xlf.account.repository.mapper.AccountInfoBakMapper;
import com.xlf.account.repository.mapper.AccountInfoExtMapper;
import com.xlf.account.repository.mapper.AccountInfoMapper;
import com.xlf.common.exception.ErrorCodeEnum;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class AccountInfoRepository {
    @Resource
    private AccountInfoMapper accountInfoMapper;
    @Resource
    private AccountInfoExtMapper accountInfoExtMapper;
    @Resource
    private AccountInfoBakMapper accountInfoBakMapper;

    public void createAccount(AccountInfoDo accountInfoDo) {
        accountInfoMapper.insertSelective(accountInfoDo);
    }

    public AccountInfoDo queryByUserIdAccountTypeForUpdate(String userId, Integer accountType) {
        return accountInfoExtMapper.queryAccountInfoForUpdate(userId, accountType);
    }

    public List<AccountInfoDo> queryByUserIdAccountType(String userId, Integer accountType) {
        AccountInfoDoExample example = new AccountInfoDoExample();
        AccountInfoDoExample.Criteria criteria = example.createCriteria()
                .andUserIdEqualTo(userId)
                .andStatusEqualTo(AccountStatusEnums.VALID.getCode());
        if (Objects.nonNull(accountType)) {
            criteria.andAccountTypeEqualTo(accountType);
        }
        return accountInfoMapper.selectByExample(example);
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

    public void deleteAccount(AccountInfoDo accountInfoDo, AccountInfoBakDo accountInfoBakDo) {
        accountInfoBakMapper.insertSelective(accountInfoBakDo);
        accountInfoMapper.deleteByPrimaryKey(accountInfoDo.getId());
    }

    public void frozen(Long accountId, Long amount) {
        int rowAffected = accountInfoExtMapper.frozen(accountId, amount);
        if (rowAffected != 1) {
            throw ErrorCodeEnum.FROZEN_ERROR.newException();
        }
    }

    public void unfrozen(Long accountId, Long amount) {
        int rowAffected = accountInfoExtMapper.unfrozen(accountId, amount);
        if (rowAffected != 1) {
            throw ErrorCodeEnum.UNFROZEN_ERROR.newException();
        }
    }

    public List<AccountInfoBakDo> queryWaitBackupAccountInfoBackup(Long startId, Integer limit) {

        AccountInfoBakDoExample example = new AccountInfoBakDoExample();
        example.createCriteria().andIdGreaterThan(startId)
                .andBackupFlowStatusIn(Lists.newArrayList(BackupFlowStatusEnums.NONE.getCode(), BackupFlowStatusEnums.BAKUPING.getCode()));
        example.setOrderByClause(" id limit " + limit);
        return accountInfoBakMapper.selectByExample(example);
    }

    public AccountInfoBakDo queryAccountInfoBakDo(Long accountId) {
        return accountInfoBakMapper.selectByPrimaryKey(accountId);
    }

    public boolean updateBackupFlowStatus(Long accountId, Integer fromStatus, Integer toStatus) {
        AccountInfoBakDo accountInfoBakDo = new AccountInfoBakDo();
        accountInfoBakDo.setBackupFlowStatus(toStatus);
        accountInfoBakDo.setUpdateTime(new Date());

        AccountInfoBakDoExample example = new AccountInfoBakDoExample();
        example.createCriteria().andIdEqualTo(accountId).andBackupFlowStatusEqualTo(fromStatus);

        return accountInfoBakMapper.updateByExampleSelective(accountInfoBakDo, example) == 1;
    }
}
