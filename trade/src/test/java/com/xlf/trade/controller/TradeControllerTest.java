package com.xlf.trade.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xlf.account.common.response.CreateAccountRsp;
import com.xlf.common.enums.CurrencyTypeEnums;
import com.xlf.common.enums.PayChannelEnums;
import com.xlf.common.response.ApiResult;
import com.xlf.common.util.JsonUtil;
import com.xlf.common.util.SnowflakeUtil;
import com.xlf.trade.TradeBaseTest;
import com.xlf.trade.vo.request.CreateTradeAccountReq;
import com.xlf.trade.vo.request.TradeRechargeReq;
import org.junit.Test;
import org.springframework.util.Assert;

public class TradeControllerTest extends TradeBaseTest {

    @Test
    public void testRecharge() throws Exception {
        TradeRechargeReq req = new TradeRechargeReq();
        req.setTransId(SnowflakeUtil.nextIdString());
        req.setUserId(userId1);
        req.setAmount(10000L);
        req.setPayChannel(PayChannelEnums.WECHAT_PAY.getCode());
        String url = "/trade/recharge";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<Object> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<Object>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");

    }
}
