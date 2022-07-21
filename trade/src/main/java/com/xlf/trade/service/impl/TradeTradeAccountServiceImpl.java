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
import com.xlf.trade.remote.third.wechat.vo.WechatCreateAccountResult;
import com.xlf.trade.remote.third.wechat.vo.WechatRechargeResult;
import com.xlf.trade.service.TradeAccountService;
import com.xlf.trade.vo.request.CreateTradeAccountReq;
import com.xlf.trade.vo.request.TradeRechargeReq;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public void createAccount(CreateTradeAccountReq req) {
        PayChannelEnums payChannelEnums = getPayChannel(req.getPayChannel());
        CurrencyTypeEnums currencyTypeEnums = CurrencyTypeEnums.parse(req.getCurrencyType());
        if (Objects.isNull(currencyTypeEnums)) {
            currencyTypeEnums = payChannelEnums.getCurrencyTypeEnums()[0];
            req.setCurrencyType(currencyTypeEnums.getCode());
        }
        String lockKey = String.format("createAccount_%s_%s", req.getTransId(), req.getUserId());
        redisLockService.runInRedisLock(lockKey, () -> {
            // account exist
            if (Objects.nonNull(queryAccountInfo(req.getUserId(), payChannelEnums))) {
                log.info("userId:{},payChannel:{} already open account", req.getUserId(), payChannelEnums.name());
                return null;
            }
            PayOrderDo payOrderDo = new PayOrderDo();
            payOrderDo.setOrderId(SnowflakeUtil.nextId());
            payOrderDo.setUserId(req.getUserId());
            payOrderDo.setTransId(req.getTransId());
            payOrderDo.setPayChannel(req.getPayChannel());
            payOrderDo.setOperateType(PayOrderOperateTypeEnums.CREATE_ACCOUNT.getCode());
            payOrderDo.setCurrencyType(req.getCurrencyType());
            payOrderDo.setStatus(PayOrderStatusEnums.INIT.getCode());
            payOrderDo.setCreateBy(req.getUserId());
            payOrderDo.setOrderTime(new Date());
            payOrderDo.setStatus(PayOrderStatusEnums.INIT.getCode());

            thirdRemoteFactory.getServiceByPayChannel(payChannelEnums)
                    .createAccount(req, payOrderDo, WechatCreateAccountResult.class);
            accountRemoteService.createAccount(req, payOrderDo, payChannelEnums.getAccountTypeEnums());

            return null;
        });
    }


    @Override
    public void recharge(TradeRechargeReq req) {
        PayChannelEnums payChannelEnums = getPayChannel(req.getPayChannel());

        String lockKey = String.format("recharge%s_%s", req.getTransId(), req.getUserId());
        redisLockService.runInRedisLock(lockKey, () -> {
            // account exist
            QueryAccountInfoRsp accountInfoRsp = queryAccountInfo(req.getUserId(), payChannelEnums);
            if (Objects.isNull(accountInfoRsp)) {
                throw ErrorCodeEnum.ACCOUNT_NOT_EXIST.newException();
            }
            PayOrderDo payOrderDo = new PayOrderDo();
            payOrderDo.setOrderId(SnowflakeUtil.nextId());
            payOrderDo.setUserId(req.getUserId());
            payOrderDo.setTransId(req.getTransId());
            payOrderDo.setPayChannel(req.getPayChannel());
            payOrderDo.setOperateType(PayOrderOperateTypeEnums.RECHARGE.getCode());
            payOrderDo.setCurrencyType(accountInfoRsp.getCurrencyType());
            payOrderDo.setStatus(PayOrderStatusEnums.INIT.getCode());
            payOrderDo.setCreateBy(req.getUserId());
            payOrderDo.setOrderTime(new Date());
            payOrderDo.setStatus(PayOrderStatusEnums.INIT.getCode());

            thirdRemoteFactory.getServiceByPayChannel(payChannelEnums)
                    .recharge(req, payOrderDo, WechatRechargeResult.class);
            accountRemoteService.recharge(req, payOrderDo, payChannelEnums.getAccountTypeEnums());

            return null;
        });
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
