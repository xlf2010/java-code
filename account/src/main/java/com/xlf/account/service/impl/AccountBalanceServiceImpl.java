package com.xlf.account.service.impl;

import com.xlf.account.entity.AccountFlowDo;
import com.xlf.account.entity.AccountInfoDo;
import com.xlf.account.enums.AccountStatusEnums;
import com.xlf.account.repository.AccountFlowRepository;
import com.xlf.account.repository.AccountInfoRepository;
import com.xlf.account.service.AccountBalanceService;
import com.xlf.account.service.AccountFlowService;
import com.xlf.account.vo.request.RechargeReq;
import com.xlf.account.vo.request.TransactionReq;
import com.xlf.account.vo.request.WithdrawReq;
import com.xlf.common.exception.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AccountBalanceServiceImpl implements AccountBalanceService {
    @Resource
    private AccountFlowService accountFlowService;
    @Resource
    private AccountInfoRepository accountInfoRepository;
    @Resource
    private AccountFlowRepository accountFlowRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recharge(RechargeReq req) {
        AccountInfoDo accountInfoDo = queryAccountInfoForUpdate(req.getUserId(), req.getAccountType());

        boolean recharge = accountInfoRepository.recharge(accountInfoDo.getId(), req.getAmount());
        if (!recharge) {
            log.info("update account info error,update result:{}", false);
            throw ErrorCodeEnum.RECHARGE_ERROR.newException();
        }
        AccountFlowDo accountFlow = accountFlowService.recharge(accountInfoDo, req);
        accountFlowRepository.insert(accountFlow);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void withdraw(WithdrawReq req) {
        AccountInfoDo accountInfoDo = queryAccountInfoForUpdate(req.getUserId(), req.getAccountType());

        boolean withdraw = accountInfoRepository.withdraw(accountInfoDo.getId(), req.getAmount());
        if (!withdraw) {
            log.info("update account info error,update result:{}", false);
            throw ErrorCodeEnum.WITHDRAW_ERROR.newException();
        }
        AccountFlowDo accountFlow = accountFlowService.withdraw(accountInfoDo, req);
        accountFlowRepository.insert(accountFlow);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transaction(TransactionReq req) {
        // serial lock account to avoid dead lock
        AccountInfoDo fromAccount;
        AccountInfoDo toAccount;
        if (req.getUserId().compareTo(req.getToUserId()) < 0) {
            fromAccount = queryAccountInfoForUpdate(req.getUserId(), req.getToAccountType());
            toAccount = queryAccountInfoForUpdate(req.getToUserId(), req.getToAccountType());
        } else {
            toAccount = queryAccountInfoForUpdate(req.getToUserId(), req.getToAccountType());
            fromAccount = queryAccountInfoForUpdate(req.getUserId(), req.getToAccountType());
        }

        accountInfoRepository.transaction(fromAccount, toAccount, req.getAmount());
        List<AccountFlowDo> accountFlowDos = accountFlowService.transaction(fromAccount, toAccount, req);
        accountFlowRepository.insertBatch(accountFlowDos);
    }

    private AccountInfoDo queryAccountInfoForUpdate(String userId, Integer accountType) {
        AccountInfoDo accountInfoDo = accountInfoRepository.queryByUserIdAccountTypeForUpdate(userId, accountType);
        if (Objects.isNull(accountInfoDo)) {
            throw ErrorCodeEnum.ACCOUNT_NOT_EXIST.newException();
        }
        if (accountInfoDo.getStatus() != AccountStatusEnums.VALID.getCode()) {
            throw ErrorCodeEnum.ACCOUNT_STATUS_ERROR.newException();
        }
        return accountInfoDo;
    }
}
