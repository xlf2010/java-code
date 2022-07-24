package com.xlf.trade.service.impl;

import com.xlf.account.common.response.QueryAccountInfoRsp;
import com.xlf.common.enums.CurrencyTypeEnums;
import com.xlf.common.enums.PayChannelEnums;
import com.xlf.common.exception.BusinessException;
import com.xlf.common.exception.ErrorCodeEnum;
import com.xlf.common.lock.RedisLockService;
import com.xlf.common.response.ApiResult;
import com.xlf.common.util.SnowflakeUtil;
import com.xlf.trade.entity.PayOrderDo;
import com.xlf.trade.enums.PayOrderOperateTypeEnums;
import com.xlf.trade.enums.PayOrderStatusEnums;
import com.xlf.trade.remote.account.AccountRemoteService;
import com.xlf.trade.remote.account.AccountServiceFeign;
import com.xlf.trade.remote.third.ThirdRemoteFactory;
import com.xlf.trade.repository.PayOrderRepository;
import com.xlf.trade.service.TradeAccountService;
import com.xlf.trade.vo.request.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class TradeTradeAccountServiceImpl implements TradeAccountService {
    @Resource
    private AccountServiceFeign accountServiceFeign;
    @Resource
    private ThirdRemoteFactory thirdRemoteFactory;
    @Resource
    private AccountRemoteService accountRemoteService;
    @Resource
    private RedisLockService redisLockService;
    @Resource
    private PayOrderRepository payOrderRepository;

    @Override
    public void createAccount(CreateTradeAccountReq req) {
        PayChannelEnums payChannelEnums = getPayChannel(req.getPayChannel());
        CurrencyTypeEnums currencyTypeEnums = CurrencyTypeEnums.parse(req.getCurrencyType());
        if (Objects.isNull(currencyTypeEnums)) {
            currencyTypeEnums = payChannelEnums.getCurrencyTypeEnums()[0];
            req.setCurrencyType(currencyTypeEnums.getCode());
        }
        String lockKey = String.format("createAccount_%s_%s", req.getTransId(), req.getUserId());
        PayOrderOperateTypeEnums operateTypeEnums = PayOrderOperateTypeEnums.CREATE_ACCOUNT;
        redisLockService.runInRedisLock(lockKey, () -> {
            // account exist
            if (Objects.nonNull(queryAccountInfo(req.getUserId(), payChannelEnums))) {
                log.info("userId:{},payChannel:{} already open account", req.getUserId(), payChannelEnums.name());
                return null;
            }
            PayOrderDo payOrderDo = initPayOrderDo(req.getCurrencyType(), req, operateTypeEnums);

            thirdRemoteFactory.getServiceByPayChannel(payChannelEnums)
                    .createAccount(req, payOrderDo);
            accountRemoteService.createAccount(req, payOrderDo, payChannelEnums.getAccountTypeEnums());

            return null;
        });
    }


    @Override
    public void recharge(TradeRechargeReq req) {
        PayChannelEnums payChannelEnums = getPayChannel(req.getPayChannel());

        String lockKey = String.format("recharge%s_%s", req.getTransId(), req.getUserId());
        PayOrderOperateTypeEnums operateTypeEnums = PayOrderOperateTypeEnums.RECHARGE;
        redisLockService.runInRedisLock(lockKey, () -> {
            // transId handle success?
            if (hasHandleSuccess(req.getTransId(), operateTypeEnums, payChannelEnums)) {
                log.info("transId:{},pay channel:{}, recharge handling successfully, do nothing", req.getTransId(), payChannelEnums);
                return null;
            }
            // account exist
            QueryAccountInfoRsp accountInfoRsp = queryAccountInfo(req.getUserId(), payChannelEnums);
            if (Objects.isNull(accountInfoRsp)) {
                throw ErrorCodeEnum.ACCOUNT_NOT_EXIST.newException();
            }
            PayOrderDo payOrderDo = initPayOrderDo(accountInfoRsp.getCurrencyType(), req, operateTypeEnums);
            payOrderDo.setAmount(req.getAmount());

            thirdRemoteFactory.getServiceByPayChannel(payChannelEnums)
                    .recharge(req, payOrderDo);
            accountRemoteService.recharge(req, payOrderDo, payChannelEnums.getAccountTypeEnums());

            return null;
        });
    }

    @Override
    public void withdraw(TradeWithdrawReq req) {
        PayChannelEnums payChannelEnums = getPayChannel(req.getPayChannel());

        String lockKey = String.format("withdraw_%s_%s", req.getTransId(), req.getUserId());
        PayOrderOperateTypeEnums operateTypeEnums = PayOrderOperateTypeEnums.WITHDRAW;
        redisLockService.runInRedisLock(lockKey, () -> {
            // transId handle success?
            if (hasHandleSuccess(req.getTransId(), operateTypeEnums, payChannelEnums)) {
                log.info("transId:{},pay channel:{}, already withdraw successfully, do nothing", req.getTransId(), payChannelEnums);
                return null;
            }
            // account exist
            QueryAccountInfoRsp accountInfoRsp = queryAccountInfo(req.getUserId(), payChannelEnums);
            if (Objects.isNull(accountInfoRsp)) {
                throw ErrorCodeEnum.ACCOUNT_NOT_EXIST.newException();
            }
            PayOrderDo payOrderDo = initPayOrderDo(accountInfoRsp.getCurrencyType(), req, operateTypeEnums);
            payOrderDo.setAmount(req.getAmount());

            thirdRemoteFactory.getServiceByPayChannel(payChannelEnums)
                    .withdraw(req, payOrderDo);
            accountRemoteService.withdraw(req, payOrderDo, payChannelEnums.getAccountTypeEnums());

            return null;
        });
    }

    @Override
    public void transaction(TradeTransactionReq req) {
        PayChannelEnums payChannelEnums = getPayChannel(req.getPayChannel());

        String lockKey = String.format("transaction%s_%s", req.getTransId(), req.getUserId());
        PayOrderOperateTypeEnums operateTypeEnums = PayOrderOperateTypeEnums.TRANSACTION;
        redisLockService.runInRedisLock(lockKey, () -> {
            // transId handle success?
            if (hasHandleSuccess(req.getTransId(), operateTypeEnums, payChannelEnums)) {
                log.info("transId:{},pay channel:{}, already transaction successfully, do nothing", req.getTransId(), payChannelEnums);
                return null;
            }
            // account exist
            QueryAccountInfoRsp accountInfoRsp = queryAccountInfo(req.getUserId(), payChannelEnums);
            if (Objects.isNull(accountInfoRsp)) {
                throw ErrorCodeEnum.ACCOUNT_NOT_EXIST.newException();
            }
            QueryAccountInfoRsp toAccountInfoRsp = queryAccountInfo(req.getToUserId(), payChannelEnums);
            if (Objects.isNull(toAccountInfoRsp)) {
                throw ErrorCodeEnum.ACCOUNT_NOT_EXIST.newException();
            }
            PayOrderDo payOrderDo = initPayOrderDo(accountInfoRsp.getCurrencyType(), req, operateTypeEnums);
            payOrderDo.setAmount(req.getAmount());
            payOrderDo.setToUserId(req.getToUserId());

            thirdRemoteFactory.getServiceByPayChannel(payChannelEnums)
                    .transaction(req, payOrderDo);
            accountRemoteService.transaction(req, payOrderDo, payChannelEnums.getAccountTypeEnums());
            return null;
        });
    }

    @Override
    public void frozen(TradeFrozenReq req) {
        PayChannelEnums payChannelEnums = getPayChannel(req.getPayChannel());

        String lockKey = String.format("frozen_%s_%s", req.getTransId(), req.getUserId());
        PayOrderOperateTypeEnums operateTypeEnums = PayOrderOperateTypeEnums.FROZEN;
        redisLockService.runInRedisLock(lockKey, () -> {
            // transId handle success?
            if (hasHandleSuccess(req.getTransId(), operateTypeEnums, payChannelEnums)) {
                log.info("transId:{},pay channel:{}, already frozen successfully, do nothing", req.getTransId(), payChannelEnums);
                return null;
            }
            // account exist
            QueryAccountInfoRsp accountInfoRsp = queryAccountInfo(req.getUserId(), payChannelEnums);
            if (Objects.isNull(accountInfoRsp)) {
                throw ErrorCodeEnum.ACCOUNT_NOT_EXIST.newException();
            }
            PayOrderDo payOrderDo = initPayOrderDo(accountInfoRsp.getCurrencyType(), req, operateTypeEnums);
            payOrderDo.setAmount(req.getAmount());

            thirdRemoteFactory.getServiceByPayChannel(payChannelEnums)
                    .frozen(req, payOrderDo);
            accountRemoteService.frozen(req, payOrderDo, payChannelEnums.getAccountTypeEnums());

            return null;
        });
    }

    @Override
    public void unfrozen(TradeUnfrozenReq req) {
        PayChannelEnums payChannelEnums = getPayChannel(req.getPayChannel());

        String lockKey = String.format("unfrozen_%s_%s", req.getTransId(), req.getUserId());
        PayOrderOperateTypeEnums operateTypeEnums = PayOrderOperateTypeEnums.UNFROZEN;
        redisLockService.runInRedisLock(lockKey, () -> {
            // transId handle success?
            if (hasHandleSuccess(req.getTransId(), operateTypeEnums, payChannelEnums)) {
                log.info("transId:{},pay channel:{}, already unfrozen successfully, do nothing", req.getTransId(), payChannelEnums);
                return null;
            }
            // account exist
            QueryAccountInfoRsp accountInfoRsp = queryAccountInfo(req.getUserId(), payChannelEnums);
            if (Objects.isNull(accountInfoRsp)) {
                throw ErrorCodeEnum.ACCOUNT_NOT_EXIST.newException();
            }
            PayOrderDo payOrderDo = initPayOrderDo(accountInfoRsp.getCurrencyType(), req, operateTypeEnums);
            payOrderDo.setAmount(req.getAmount());

            thirdRemoteFactory.getServiceByPayChannel(payChannelEnums)
                    .unfrozen(req, payOrderDo);
            accountRemoteService.unfrozen(req, payOrderDo, payChannelEnums.getAccountTypeEnums());

            return null;
        });
    }

    private PayOrderDo initPayOrderDo(String currencyType, BaseReq req, PayOrderOperateTypeEnums operateTypeEnums) {
        PayOrderDo payOrderDo = new PayOrderDo();
        payOrderDo.setOrderId(SnowflakeUtil.nextId());
        payOrderDo.setUserId(req.getUserId());
        payOrderDo.setTransId(req.getTransId());
        payOrderDo.setPayChannel(req.getPayChannel());
        payOrderDo.setOperateType(operateTypeEnums.getCode());
        if (StringUtils.isNotBlank(currencyType)) {
            payOrderDo.setCurrencyType(currencyType);
        }
        payOrderDo.setCreateBy(req.getUserId());
        payOrderDo.setOrderTime(new Date());
        payOrderDo.setStatus(PayOrderStatusEnums.INIT.getCode());
        return payOrderDo;
    }

    private boolean hasHandleSuccess(String transId, PayOrderOperateTypeEnums operateTypeEnums, PayChannelEnums payChannelEnums) {
        List<PayOrderDo> payOrderDos = payOrderRepository.queryPayOrder(transId, operateTypeEnums, payChannelEnums);
        return payOrderDos.stream().anyMatch(payOrderDo -> payOrderDo.getStatus() >= PayOrderStatusEnums.CALLING_ACCOUNT.getCode());
    }

    private PayChannelEnums getPayChannel(Integer payChannel) {
        PayChannelEnums payChannelEnums = PayChannelEnums.parse(payChannel);
        if (Objects.isNull(payChannelEnums)) {
            throw new BusinessException(ErrorCodeEnum.REQUEST_PARAMS_FAIL.getCode(), "payChannel illegal" + payChannel);
        }
        return payChannelEnums;
    }

    private QueryAccountInfoRsp queryAccountInfo(String userId, PayChannelEnums payChannelEnums) {
        ApiResult<List<QueryAccountInfoRsp>> apiResult = accountServiceFeign.queryAccountInfo(userId, payChannelEnums.getAccountTypeEnums().getCode());
        if (!apiResult.isSuccess()) {
            throw ErrorCodeEnum.ACCOUNT_ERROR.newException();
        }
        return CollectionUtils.isEmpty(apiResult.getData()) ? null : apiResult.getData().get(0);
    }
}
