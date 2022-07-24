package com.xlf.trade.remote.third.wechat;

import com.xlf.common.enums.PayChannelEnums;
import com.xlf.common.exception.ErrorCodeEnum;
import com.xlf.common.response.ApiResult;
import com.xlf.common.util.JsonUtil;
import com.xlf.trade.entity.PayOrderDo;
import com.xlf.trade.enums.PayOrderOperateTypeEnums;
import com.xlf.trade.remote.third.AbstractThirdRemoteService;
import com.xlf.trade.remote.third.ThirdRemoteAnnotation;
import com.xlf.trade.remote.third.wechat.vo.*;
import com.xlf.trade.repository.PayOrderRepository;
import com.xlf.trade.vo.request.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
@ThirdRemoteAnnotation(PayChannelEnums.WECHAT_PAY)
public class WechatRemoteService extends AbstractThirdRemoteService {

    @Resource
    private PayOrderRepository payOrderRepository;

    @Override
    public WechatCreateAccountResult createAccount(CreateTradeAccountReq req, PayOrderDo payOrderDo) {
        payOrderDo.setRequestUrl("https://test.wechat.com/open_account");
        payOrderDo.setRequestParam(JsonUtil.toJsonString(req));
        payOrderRepository.insert(payOrderDo);
        ApiResult<String> apiResult = callPay(payOrderDo);
        afterCallingPayChannel(payOrderDo, apiResult);

        return JsonUtil.fromJson(apiResult.getData(), WechatCreateAccountResult.class);
    }

    @Override
    public WechatRechargeResult recharge(TradeRechargeReq req, PayOrderDo payOrderDo) {
        payOrderDo.setRequestUrl("https://test.wechat.com/recharge");
        payOrderDo.setRequestParam(JsonUtil.toJsonString(req));
        payOrderRepository.insert(payOrderDo);
        ApiResult<String> apiResult = callPay(payOrderDo);
        afterCallingPayChannel(payOrderDo, apiResult);
        return JsonUtil.fromJson(apiResult.getData(), WechatRechargeResult.class);
    }

    @Override
    public WechatWithdrawResult withdraw(TradeWithdrawReq req, PayOrderDo payOrderDo) {
        payOrderDo.setRequestUrl("https://test.wechat.com/withdraw");
        payOrderDo.setRequestParam(JsonUtil.toJsonString(req));
        payOrderRepository.insert(payOrderDo);
        ApiResult<String> apiResult = callPay(payOrderDo);
        afterCallingPayChannel(payOrderDo, apiResult);
        return JsonUtil.fromJson(apiResult.getData(), WechatWithdrawResult.class);
    }

    @Override
    public WechatTransactionResult transaction(TradeTransactionReq req, PayOrderDo payOrderDo) {
        payOrderDo.setRequestUrl("https://test.wechat.com/transaction");
        payOrderDo.setRequestParam(JsonUtil.toJsonString(req));
        payOrderRepository.insert(payOrderDo);
        ApiResult<String> apiResult = callPay(payOrderDo);
        afterCallingPayChannel(payOrderDo, apiResult);
        return JsonUtil.fromJson(apiResult.getData(), WechatTransactionResult.class);
    }

    @Override
    public WechatFrozenResult frozen(TradeFrozenReq req, PayOrderDo payOrderDo) {
        payOrderDo.setRequestUrl("https://test.wechat.com/frozen");
        payOrderDo.setRequestParam(JsonUtil.toJsonString(req));
        payOrderRepository.insert(payOrderDo);
        ApiResult<String> apiResult = callPay(payOrderDo);
        afterCallingPayChannel(payOrderDo, apiResult);
        return JsonUtil.fromJson(apiResult.getData(), WechatFrozenResult.class);
    }

    @Override
    public WechatUnfrozenResult unfrozen(TradeUnfrozenReq req, PayOrderDo payOrderDo) {
        payOrderDo.setRequestUrl("https://test.wechat.com/unfrozen");
        payOrderDo.setRequestParam(JsonUtil.toJsonString(req));
        payOrderRepository.insert(payOrderDo);
        ApiResult<String> apiResult = callPay(payOrderDo);
        afterCallingPayChannel(payOrderDo, apiResult);
        return JsonUtil.fromJson(apiResult.getData(), WechatUnfrozenResult.class);
    }

    private ApiResult<String> callPay(PayOrderDo payOrderDo) {
        if (!beforeCallingPayChannel(payOrderDo.getOrderId(), payOrderDo.getRequestUrl(), payOrderDo.getRequestParam())) {
            return ApiResult.fail(ErrorCodeEnum.PAY_ERROR.getCode(), "before calling, update payOrderStatus error");
        }
        String resultStr = callHttp(payOrderDo);
        WechatBaseResult baseResult = JsonUtil.fromJson(resultStr, WechatBaseResult.class);
        if (Objects.isNull(baseResult)) {
            throw ErrorCodeEnum.NETWORK_ERROR.newException();
        }
        ApiResult<String> apiResult;
        if (baseResult.getCode() == WechatBaseResult.SUCCESS_CODE) {
            apiResult = ApiResult.success(resultStr);
        } else {
            apiResult = ApiResult.fail(baseResult.getCode(), baseResult.getMsg(), resultStr);
        }
        return apiResult;
    }

    private String callHttp(PayOrderDo payOrderDo) {
        if (PayOrderOperateTypeEnums.CREATE_ACCOUNT.getCode() == payOrderDo.getOperateType()) {
            return mockCreateAccountResult();
        }
        return mockResult();
    }


}
