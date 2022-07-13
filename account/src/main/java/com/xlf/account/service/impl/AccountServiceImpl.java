package com.xlf.account.service.impl;

import com.xlf.account.entity.AccountFlowDo;
import com.xlf.account.entity.AccountInfoDo;
import com.xlf.account.enums.AccountCreateTypeEnums;
import com.xlf.account.enums.AccountFlowOperateTypeEnums;
import com.xlf.account.enums.AccountTypeEnums;
import com.xlf.account.enums.CurrencyTypeEnums;
import com.xlf.account.repository.AccountFlowRepository;
import com.xlf.account.repository.AccountInfoRepository;
import com.xlf.account.service.AccountBalanceService;
import com.xlf.account.service.AccountService;
import com.xlf.account.vo.request.*;
import com.xlf.account.vo.response.CreateAccountRsp;
import com.xlf.common.exception.BusinessException;
import com.xlf.common.exception.ErrorCodeEnum;
import com.xlf.common.util.SnowflakeUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountInfoRepository accountInfoRepository;
    @Resource
    private AccountFlowRepository accountFlowRepository;
    @Resource
    private AccountBalanceService accountBalanceService;
    @Resource
    private RedissonClient redissonClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreateAccountRsp createAccount(CreateAccountReq req) {

        checkCreateAccountReq(req);
        AccountInfoDo accountInfo = new AccountInfoDo();
        accountInfo.setId(SnowflakeUtil.nextId());
        accountInfo.setAccountName(req.getAccountName());
        accountInfo.setAccountType(req.getAccountType());
        accountInfo.setCurrencyType(req.getCurrencyType());
        accountInfo.setUserId(req.getUserId());
        accountInfo.setUserName(req.getUserName());
        if (Objects.nonNull(req.getCreateType())) {
            accountInfo.setAccountType(req.getAccountType());
        }
        if (StringUtils.isNotBlank(req.getCreateBy())) {
            accountInfo.setCreateBy(req.getCreateBy());
            accountInfo.setUpdateBy(req.getCreateBy());
        }
        accountInfoRepository.createAccount(accountInfo);

        CreateAccountRsp rsp = new CreateAccountRsp();
        rsp.setAccountId(accountInfo.getId().toString());
        return rsp;
    }

    private void checkCreateAccountReq(CreateAccountReq req) {
        if (Objects.isNull(AccountTypeEnums.parse(req.getAccountType()))) {
            throw new BusinessException(ErrorCodeEnum.REQUEST_PARAMS_FAIL.getCode(), "accountType  illegal");
        }
        if (Objects.isNull(CurrencyTypeEnums.parse(req.getCurrencyType()))) {
            throw new BusinessException(ErrorCodeEnum.REQUEST_PARAMS_FAIL.getCode(), "currencyType  illegal");
        }
        if (Objects.nonNull(req.getCreateType()) && Objects.isNull(AccountCreateTypeEnums.parse(req.getCreateType()))) {
            throw new BusinessException(ErrorCodeEnum.REQUEST_PARAMS_FAIL.getCode(), "createType  illegal");
        }
    }

    @Override
    public void recharge(RechargeReq req) {
        String lockKey = String.format("recharge:%s:%s:%d", req.getTransId(), req.getUserId(), req.getAccountType());
        RLock lock = redissonClient.getLock(lockKey);
        boolean lockSuccess = false;
        try {
            lockSuccess = lock.tryLock();
            if (!lockSuccess) {
                throw ErrorCodeEnum.DATA_PROCESSING.newException();
            }
            // has been recharge success, do nothing
            List<AccountFlowDo> accountFlowDos = accountFlowRepository.queryByTransIdOperateType(req.getTransId(), AccountFlowOperateTypeEnums.RECHARGE.getCode());
            if (!CollectionUtils.isEmpty(accountFlowDos)) {
                return;
            }
            accountBalanceService.recharge(req);
        } finally {
            if (lockSuccess) {
                lock.unlock();
            }
        }
    }

    @Override
    public void withdraw(WithdrawReq req) {
        String lockKey = String.format("withdraw:%s:%s:%d", req.getTransId(), req.getUserId(), req.getAccountType());
        RLock lock = redissonClient.getLock(lockKey);
        boolean lockSuccess = false;
        try {
            lockSuccess = lock.tryLock();
            if (!lockSuccess) {
                throw ErrorCodeEnum.DATA_PROCESSING.newException();
            }
            // has been recharge success, do nothing
            List<AccountFlowDo> accountFlowDos = accountFlowRepository.queryByTransIdOperateType(req.getTransId(), AccountFlowOperateTypeEnums.WITHDRAW.getCode());
            if (!CollectionUtils.isEmpty(accountFlowDos)) {
                return;
            }
            accountBalanceService.withdraw(req);
        } finally {
            if (lockSuccess) {
                lock.unlock();
            }
        }
    }

    @Override
    public void transaction(TransactionReq req) {
        String lockKey = String.format("transaction:%s:%s:%d", req.getTransId(), req.getUserId(), req.getAccountType());
        RLock lock = redissonClient.getLock(lockKey);
        boolean lockSuccess = false;
        try {
            lockSuccess = lock.tryLock();
            if (!lockSuccess) {
                throw ErrorCodeEnum.DATA_PROCESSING.newException();
            }

            // has been recharge success, do nothing
            List<AccountFlowDo> accountFlowDos = accountFlowRepository.queryByTransIdOperateType(req.getTransId(),
                    AccountFlowOperateTypeEnums.TRANSACTION_OUT.getCode());
            if (!CollectionUtils.isEmpty(accountFlowDos)) {
                return;
            }
            // same account do nothing
            if (StringUtils.equals(req.getUserId(), req.getToUserId())
                    && req.getAccountType().equals(req.getToAccountType())) {
                return;
            }
            accountBalanceService.transaction(req);
        } finally {
            if (lockSuccess) {
                lock.unlock();
            }
        }
    }

    @Override
    public void deleteAccount(DeleteAccountReq req) {
        String lockKey = String.format("deleteAccount:%s:%s:%d", req.getTransId(), req.getUserId(), req.getAccountType());
        RLock lock = redissonClient.getLock(lockKey);
        boolean lockSuccess = false;
        try {
            lockSuccess = lock.tryLock();
            if (!lockSuccess) {
                throw ErrorCodeEnum.DATA_PROCESSING.newException();
            }

            // has been recharge success, do nothing
            List<AccountFlowDo> accountFlowDos = accountFlowRepository.queryByTransIdOperateType(req.getTransId(),
                    AccountFlowOperateTypeEnums.DELETE_ACCOUNT.getCode());
            if (!CollectionUtils.isEmpty(accountFlowDos)) {
                return;
            }

        } finally {
            if (lockSuccess) {
                lock.unlock();
            }
        }
    }
}
