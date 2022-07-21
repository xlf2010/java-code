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
import org.junit.Test;
import org.springframework.util.Assert;

public class TradeAccountControllerTest extends TradeBaseTest {
    @Test
    public void testCreateAccount() throws Exception {
        createAccount(userId1, PayChannelEnums.WECHAT_PAY);
    }

    private void createAccount(String userId, PayChannelEnums payChannelEnums) throws Exception {
        CreateTradeAccountReq req = new CreateTradeAccountReq();
        req.setTransId(SnowflakeUtil.nextIdString());
        req.setUserId(userId);
        req.setAccountName("name" + System.currentTimeMillis());
        req.setUserName("test user name");
        req.setPayChannel(payChannelEnums.getCode());
        req.setCurrencyType(CurrencyTypeEnums.CNY.getCode());
        String url = "/trade/account/create";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<CreateAccountRsp> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<CreateAccountRsp>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");

    }
}
