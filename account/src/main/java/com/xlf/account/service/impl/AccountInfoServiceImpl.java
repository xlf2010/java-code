package com.xlf.account.service.impl;

import com.xlf.account.entity.AccountFlowDo;
import com.xlf.account.entity.AccountInfoBakDo;
import com.xlf.account.entity.AccountInfoDo;
import com.xlf.account.enums.AccountFlowOperateTypeEnums;
import com.xlf.account.enums.AccountStatusEnums;
import com.xlf.account.mapper.AccountInfoMapper;
import com.xlf.account.repository.AccountFlowRepository;
import com.xlf.account.repository.AccountInfoRepository;
import com.xlf.account.service.AccountFlowService;
import com.xlf.account.service.AccountInfoService;
import com.xlf.account.vo.request.CreateAccountReq;
import com.xlf.account.vo.request.DeleteAccountReq;
import com.xlf.common.exception.BusinessException;
import com.xlf.common.exception.ErrorCodeEnum;
import com.xlf.common.util.SnowflakeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {
    @Resource
    private AccountInfoRepository accountInfoRepository;
    @Resource
    private AccountFlowService accountFlowService;
    @Resource
    private AccountFlowRepository accountFlowRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createAccount(CreateAccountReq req) {
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
        }
        accountInfoRepository.createAccount(accountInfo);
        AccountFlowDo accountFlowDo = accountFlowService.createDeleteAccount(accountInfo, req.getTransId(), AccountFlowOperateTypeEnums.CREATE_ACCOUNT);
        accountFlowRepository.insert(accountFlowDo);

        return accountInfo.getId();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccountInfoDo deleteAccount(DeleteAccountReq req) {
        AccountInfoDo accountInfoDo = accountInfoRepository.queryByUserIdAccountTypeForUpdate(req.getUserId(), req.getAccountType());
        if (Objects.isNull(accountInfoDo)) {
            return null;
        }
        if (accountInfoDo.getBalance() > 0 || accountInfoDo.getFrozenBalance() > 0) {
            throw new BusinessException(ErrorCodeEnum.ACCOUNT_HAS_BALANCE_ERROR.getCode(), "account still has balance or frozen balance,please withdraw");
        }
        if (accountInfoDo.getStatus() != AccountStatusEnums.VALID.getCode()) {
            throw new BusinessException(ErrorCodeEnum.ACCOUNT_STATUS_ERROR.getCode(), "account status not valid");
        }
        AccountInfoBakDo accountInfoBakDo = AccountInfoMapper.INSTANCE.toAccountInfoBakDo(accountInfoDo);
        accountInfoRepository.deleteAccount(accountInfoDo, accountInfoBakDo);

        AccountFlowDo accountFlowDo = accountFlowService.createDeleteAccount(accountInfoDo, req.getTransId(), AccountFlowOperateTypeEnums.DELETE_ACCOUNT);
        accountFlowRepository.insert(accountFlowDo);
        return accountInfoDo;
    }
}
