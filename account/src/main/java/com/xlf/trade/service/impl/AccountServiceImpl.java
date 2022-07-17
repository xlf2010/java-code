package com.xlf.trade.service.impl;

import com.xlf.trade.entity.AccountFlowBakDo;
import com.xlf.trade.entity.AccountFlowDo;
import com.xlf.trade.entity.AccountInfoBakDo;
import com.xlf.trade.entity.AccountInfoDo;
import com.xlf.trade.enums.*;
import com.xlf.trade.mapstruct.mapper.AccountFlowMapper;
import com.xlf.trade.repository.AccountFlowRepository;
import com.xlf.trade.repository.AccountInfoRepository;
import com.xlf.trade.service.AccountBalanceService;
import com.xlf.trade.service.AccountInfoService;
import com.xlf.trade.service.AccountService;
import com.xlf.trade.service.RedisLockService;
import com.xlf.trade.vo.request.*;
import com.xlf.trade.vo.response.CreateAccountRsp;
import com.xlf.common.exception.BusinessException;
import com.xlf.common.exception.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountInfoRepository accountInfoRepository;
    @Resource
    private AccountFlowRepository accountFlowRepository;
    @Resource
    private AccountBalanceService accountBalanceService;
    @Resource
    private AccountInfoService accountInfoService;
    @Resource
    private RedisLockService redisLockService;
    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public CreateAccountRsp createAccount(CreateAccountReq req) {
        checkAccountEnums(req.getAccountType(), req.getCurrencyType(), req.getCreateType());

        String lockKey = String.format("createAccount:%s:%s:%d", req.getTransId(), req.getUserId(), req.getAccountType());
        return redisLockService.runInRedisLock(lockKey, () -> {
            Long accountId;
            // has been recharge success, do nothing
            List<AccountFlowDo> accountFlowDos = accountFlowRepository.queryByTransIdOperateType(req.getTransId(),
                    AccountFlowOperateTypeEnums.DELETE_ACCOUNT.getCode());
            if (!CollectionUtils.isEmpty(accountFlowDos)) {
                accountId = accountFlowDos.get(0).getAccountId();
            } else {
                accountId = accountInfoService.createAccount(req);
            }
            return new CreateAccountRsp(String.valueOf(accountId));
        });
    }

    private void checkAccountEnums(Integer accountType, String currencyType, Integer createType) {
        if (Objects.nonNull(accountType) && Objects.isNull(AccountTypeEnums.parse(accountType))) {
            throw new BusinessException(ErrorCodeEnum.REQUEST_PARAMS_FAIL.getCode(), "accountType  illegal");
        }
        if (Objects.nonNull(currencyType) && Objects.isNull(CurrencyTypeEnums.parse(currencyType))) {
            throw new BusinessException(ErrorCodeEnum.REQUEST_PARAMS_FAIL.getCode(), "currencyType  illegal");
        }
        if (Objects.nonNull(createType) && Objects.isNull(AccountCreateTypeEnums.parse(createType))) {
            throw new BusinessException(ErrorCodeEnum.REQUEST_PARAMS_FAIL.getCode(), "createType  illegal");
        }
    }

    @Override
    public void recharge(RechargeReq req) {
        checkAccountEnums(req.getAccountType(), null, null);
        String lockKey = String.format("recharge:%s:%s:%d", req.getTransId(), req.getUserId(), req.getAccountType());
        redisLockService.runInRedisLock(lockKey, () -> {
            if (dontHasFlowSuccess(req.getTransId(), AccountFlowOperateTypeEnums.RECHARGE)) {
                accountBalanceService.recharge(req);
            }
            return null;
        });
    }

    @Override
    public void withdraw(WithdrawReq req) {
        checkAccountEnums(req.getAccountType(), null, null);
        String lockKey = String.format("withdraw:%s:%s:%d", req.getTransId(), req.getUserId(), req.getAccountType());
        redisLockService.runInRedisLock(lockKey, () -> {
            if (dontHasFlowSuccess(req.getTransId(), AccountFlowOperateTypeEnums.WITHDRAW)) {
                accountBalanceService.withdraw(req);
            }
            return null;
        });
    }

    @Override
    public void transaction(TransactionReq req) {
        checkAccountEnums(req.getAccountType(), null, null);
        checkAccountEnums(req.getToAccountType(), null, null);
        // same trade do nothing
        if (StringUtils.equals(req.getUserId(), req.getToUserId())
                && req.getAccountType().equals(req.getToAccountType())) {
            return;
        }

        String lockKey = String.format("transaction:%s:%s:%d", req.getTransId(), req.getUserId(), req.getAccountType());
        redisLockService.runInRedisLock(lockKey, () -> {
            if (dontHasFlowSuccess(req.getTransId(), AccountFlowOperateTypeEnums.TRANSACTION_OUT)) {
                accountBalanceService.transaction(req);
            }
            return null;
        });
    }

    @Override
    public void frozen(FrozenReq req) {
        checkAccountEnums(req.getAccountType(), null, null);
        String lockKey = String.format("frozen:%s:%s:%d", req.getTransId(), req.getUserId(), req.getAccountType());
        redisLockService.runInRedisLock(lockKey, () -> {
            if (dontHasFlowSuccess(req.getTransId(), AccountFlowOperateTypeEnums.WITHDRAW)) {
                accountBalanceService.frozen(req);
            }
            return null;
        });
    }

    @Override
    public void unfrozen(UnfrozenReq req) {
        checkAccountEnums(req.getAccountType(), null, null);
        String lockKey = String.format("unfrozen:%s:%s:%d", req.getTransId(), req.getUserId(), req.getAccountType());
        redisLockService.runInRedisLock(lockKey, () -> {
            if (dontHasFlowSuccess(req.getTransId(), AccountFlowOperateTypeEnums.WITHDRAW)) {
                accountBalanceService.unfrozen(req);
            }
            return null;
        });
    }

    @Override
    public void deleteAccount(DeleteAccountReq req) {
        checkAccountEnums(req.getAccountType(), null, null);
        String lockKey = String.format("deleteAccount:%s:%s:%d", req.getTransId(), req.getUserId(), req.getAccountType());
        redisLockService.runInRedisLock(lockKey, () -> {
            if (dontHasFlowSuccess(req.getTransId(), AccountFlowOperateTypeEnums.DELETE_ACCOUNT)) {
                AccountInfoDo accountInfoDo = accountInfoService.deleteAccount(req);
                asyncMoveAccountFlowToBak(accountInfoDo);
            }
            return null;
        });
    }

    private void asyncMoveAccountFlowToBak(AccountInfoDo accountInfoDo) {
        if (Objects.isNull(accountInfoDo)) {
            return;
        }
        threadPoolExecutor.execute(() -> {
            moveAccountFlowToBak(accountInfoRepository.queryAccountInfoBakDo(accountInfoDo.getId()));
        });
    }

    @Override
    public void checkAndBackupAccountFlow() {
        Long startId = 0L;
        Integer limit = 100;
        while (true) {
            List<AccountInfoBakDo> accountInfoBakDos = accountInfoRepository.queryYesterdayAccountInfoBackup(startId, limit);
            if (CollectionUtils.isEmpty(accountInfoBakDos)) {
                break;
            }
            for (AccountInfoBakDo accountInfoBakDo : accountInfoBakDos) {
                moveAccountFlowToBak(accountInfoBakDo);
            }
            startId = accountInfoBakDos.get(accountInfoBakDos.size() - 1).getId();
        }
    }


    private void moveAccountFlowToBak(AccountInfoBakDo accountInfoBakDo) {
        if (Objects.isNull(accountInfoBakDo)) {
            return;
        }
        if (accountInfoBakDo.getBackupFlowStatus() != BakupFlowStatusEnums.NONE.getCode()) {
            return;
        }
        Long accountId = accountInfoBakDo.getId();
        if (!accountInfoRepository.updateBakupFlowStatus(accountId, BakupFlowStatusEnums.NONE.getCode(), BakupFlowStatusEnums.BAKUPING.getCode())) {
            return;
        }

        Long startId = 0L;
        Integer limit = 100;
        int times = 1;
        while (true) {
            List<AccountFlowDo> accountFlowDos = accountFlowRepository.queryByAccountIdPage(accountId, startId, limit);
            if (CollectionUtils.isEmpty(accountFlowDos)) {
                break;
            }
            log.info("move accountFlow to accountFlowBak times:{},data count:{}", times++, accountFlowDos.size());

            List<AccountFlowBakDo> accountFlowBakDos = new ArrayList<>(accountFlowDos.size());
            for (AccountFlowDo accountFlowDo : accountFlowDos) {
                accountFlowBakDos.add(AccountFlowMapper.INSTANCE.toAccountFlowBakDo(accountFlowDo));
            }
            accountFlowRepository.moveAccountFlowToBak(accountFlowDos, accountFlowBakDos);
            startId = accountFlowDos.get(accountFlowDos.size() - 1).getFlowId();
        }

        accountInfoRepository.updateBakupFlowStatus(accountId, BakupFlowStatusEnums.BAKUPING.getCode(), BakupFlowStatusEnums.FINISH.getCode());
    }

    private boolean dontHasFlowSuccess(String transId, AccountFlowOperateTypeEnums operateTypeEnums) {
        List<AccountFlowDo> accountFlowDos = accountFlowRepository.queryByTransIdOperateType(transId, operateTypeEnums.getCode());

        boolean dontHasRecord = CollectionUtils.isEmpty(accountFlowDos);
        if (!dontHasRecord) {
            log.info("transId:{},operateType:{} has been execute successfully", transId, operateTypeEnums);
        }
        return dontHasRecord;
    }
}
