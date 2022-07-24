package com.xlf.trade.remote.third;

import com.xlf.common.exception.ErrorCodeEnum;
import com.xlf.common.response.ApiResult;
import com.xlf.common.util.JsonUtil;
import com.xlf.common.util.SnowflakeUtil;
import com.xlf.trade.entity.PayOrderDo;
import com.xlf.trade.enums.PayOrderStatusEnums;
import com.xlf.trade.remote.third.wechat.vo.WechatBaseResult;
import com.xlf.trade.remote.third.wechat.vo.WechatCreateAccountResult;
import com.xlf.trade.repository.PayOrderRepository;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.Random;

public abstract class AbstractThirdRemoteService implements ThirdRemoteService {
    @Resource
    private PayOrderRepository payOrderRepository;

    protected boolean beforeCallingPayChannel(Long orderId, String requestUrl, String requestParam) {
        return payOrderRepository.beforeCallingPayChannel(orderId, requestUrl, requestParam);
    }


    protected boolean afterCallingPayChannel(PayOrderDo payOrderDo, ApiResult<String> apiResult) {
        int toStatus;
        String remark = null;
        if (apiResult.isSuccess()) {
            toStatus = PayOrderStatusEnums.CALLING_ACCOUNT.getCode();
        } else {
            toStatus = PayOrderStatusEnums.CALL_PAY_CHANNEL_FAIL.getCode();
            remark = apiResult.getMsg();
        }
        boolean updateSuccess = payOrderRepository.afterCalling(payOrderDo.getOrderId(),
                apiResult.getData(),
                PayOrderStatusEnums.CALLING_PAY_CHANNEL.getCode(),
                toStatus,
                remark);

        if (updateSuccess) {
            payOrderDo.setStatus(toStatus);
            if (StringUtils.isNotBlank(remark)) {
                payOrderDo.setRemark(remark);
            }
        } else {
            throw ErrorCodeEnum.SERVICE_BUSY_ERROR.newException();
        }

        if (!apiResult.isSuccess()) {
            throw ErrorCodeEnum.PAY_ERROR.newException();
        }
        return true;
    }


    protected String mockCreateAccountResult() {
        WechatCreateAccountResult result = new WechatCreateAccountResult();
        result.setCode(WechatCreateAccountResult.SUCCESS_CODE);
        result.setAccountId(SnowflakeUtil.nextIdString());
        int random = new Random().nextInt();

        if (random % 10 == 1) {
            result.setCode(-1);
            result.setMsg("name error");
        }
        return JsonUtil.toJsonString(result);
    }

    protected String mockResult() {
        WechatBaseResult result = new WechatBaseResult();
        result.setCode(WechatCreateAccountResult.SUCCESS_CODE);
        int random = new Random().nextInt();

        if (random % 2 == 1) {
            result.setCode(-1);
            result.setMsg("call error");
        }
        return JsonUtil.toJsonString(result);
    }

}
