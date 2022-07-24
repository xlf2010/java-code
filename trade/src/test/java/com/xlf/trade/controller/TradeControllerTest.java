package com.xlf.trade.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xlf.common.enums.PayChannelEnums;
import com.xlf.common.response.ApiResult;
import com.xlf.common.util.JsonUtil;
import com.xlf.common.util.SnowflakeUtil;
import com.xlf.trade.TradeBaseTest;
import com.xlf.trade.vo.request.*;
import org.junit.Test;
import org.springframework.util.Assert;

public class TradeControllerTest extends TradeBaseTest {

    @Test
    public void testRecharge() throws Exception {
        TradeRechargeReq req = new TradeRechargeReq();
        req.setTransId(SnowflakeUtil.nextIdString());
//        req.setTransId("4173476995141632");
        req.setUserId(userId1);
        req.setAmount(10000L);
        req.setPayChannel(PayChannelEnums.WECHAT_PAY.getCode());
        String url = "/trade/recharge";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<Object> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<Object>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
    }

    @Test
    public void testWithdraw() throws Exception {
        TradeWithdrawReq req = new TradeWithdrawReq();
        req.setTransId(SnowflakeUtil.nextIdString());
//        req.setTransId("4173476995141632");
        req.setUserId(userId1);
        req.setAmount(1000L);
        req.setPayChannel(PayChannelEnums.WECHAT_PAY.getCode());
        String url = "/trade/withdraw";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<Object> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<Object>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
    }

    @Test
    public void testTransaction() throws Exception {
        TradeTransactionReq req = new TradeTransactionReq();
        req.setTransId(SnowflakeUtil.nextIdString());
//        req.setTransId("4173476995141632");
        req.setUserId(userId1);
        req.setToUserId(userId2);
        req.setAmount(1000L);
        req.setPayChannel(PayChannelEnums.WECHAT_PAY.getCode());
        String url = "/trade/transaction";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<Object> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<Object>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
    }

    @Test
    public void testFrozen() throws Exception {
        TradeFrozenReq req = new TradeFrozenReq();
        req.setTransId(SnowflakeUtil.nextIdString());
//        req.setTransId("4173476995141632");
        req.setUserId(userId1);
        req.setAmount(1000L);
        req.setPayChannel(PayChannelEnums.WECHAT_PAY.getCode());
        String url = "/trade/frozen";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<Object> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<Object>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
    }

    @Test
    public void testUnfrozen() throws Exception {
        TradeUnfrozenReq req = new TradeUnfrozenReq();
        req.setTransId(SnowflakeUtil.nextIdString());
        req.setUserId(userId1);
        req.setAmount(1000L);
        req.setPayChannel(PayChannelEnums.WECHAT_PAY.getCode());
        String url = "/trade/unfrozen";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<Object> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<Object>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
    }
}
