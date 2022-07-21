package com.xlf.trade.remote.third.wechat;

import com.xlf.common.enums.PayChannelEnums;
import com.xlf.common.exception.ErrorCodeEnum;
import com.xlf.common.response.ApiResult;
import com.xlf.common.util.JsonUtil;
import com.xlf.trade.entity.PayOrderDo;
import com.xlf.trade.enums.PayOrderOperateTypeEnums;
import com.xlf.trade.remote.third.AbstractThirdRemoteService;
import com.xlf.trade.remote.third.ThirdRemoteAnnotation;
import com.xlf.trade.remote.third.wechat.vo.WechatBaseResult;
import com.xlf.trade.repository.PayOrderRepository;
import com.xlf.trade.vo.request.CreateTradeAccountReq;
import com.xlf.trade.vo.request.TradeRechargeReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
@ThirdRemoteAnnotation(PayChannelEnums.WECHAT_PAY)
public class WechatRemoteService extends AbstractThirdRemoteService {

    @Resource
    private PayOrderRepository payOrderRepository;

    @Override
    public <T> T createAccount(CreateTradeAccountReq req, PayOrderDo payOrderDo, Class<T> resultClass) {
        payOrderDo.setRequestUrl("https://test.wechat.com/open_account");
        payOrderDo.setRequestParam(JsonUtil.toJsonString(req));
        payOrderRepository.insert(payOrderDo);
        ApiResult<String> apiResult = callPay(payOrderDo);
        afterCallingPayChannel(payOrderDo, apiResult);

        return JsonUtil.fromJson(apiResult.getData(), resultClass);
    }

    @Override
    public <T> T recharge(TradeRechargeReq req, PayOrderDo payOrderDo, Class<T> resultClass) {
        payOrderDo.setRequestUrl("https://test.wechat.com/recharge");
        payOrderDo.setRequestParam(JsonUtil.toJsonString(req));
        payOrderRepository.insert(payOrderDo);
        ApiResult<String> apiResult = callPay(payOrderDo);
        afterCallingPayChannel(payOrderDo, apiResult);
        return JsonUtil.fromJson(apiResult.getData(), resultClass);
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
