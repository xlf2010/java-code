package com.xlf.account.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xlf.account.BaseTest;
import com.xlf.account.enums.AccountCreateTypeEnums;
import com.xlf.account.enums.AccountTypeEnums;
import com.xlf.account.enums.CurrencyTypeEnums;
import com.xlf.account.vo.request.*;
import com.xlf.account.vo.response.CreateAccountRsp;
import com.xlf.common.response.ApiResult;
import com.xlf.common.util.JsonUtil;
import com.xlf.common.util.SnowflakeUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.Assert;

public class AccountControllerTest extends BaseTest {

    @Test
    public void testCreateAccount() throws Exception {
        deleteAccount(userId1);
        deleteAccount(userId2);

        createAccount(userId1);
        createAccount(userId2);
    }

    private void createAccount(String userId) throws Exception {
        CreateAccountReq req = new CreateAccountReq();
        req.setTransId(String.valueOf(SnowflakeUtil.nextId()));
        req.setUserId(userId);
        req.setAccountType(AccountTypeEnums.NORMAL.getCode());
        req.setAccountName("name" + System.currentTimeMillis());
        req.setUserName("test user name");
        req.setCreateBy(req.getUserId());
        req.setCreateType(AccountCreateTypeEnums.USER.getCode());
        req.setCurrencyType(CurrencyTypeEnums.USD.getCode());
        String url = "/account/create";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<CreateAccountRsp> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<CreateAccountRsp>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
        Assert.notNull(rsp.getData(), "rsp.data can't be null");
        Assert.isTrue(StringUtils.isNotBlank(rsp.getData().getAccountId()), "rsp.data.accountId can't be null");

    }

    @Test
    public void testRecharge() throws Exception {
        RechargeReq req = new RechargeReq();
        req.setUserId(userId1);
        req.setTransId(String.valueOf(SnowflakeUtil.nextId()));
        req.setAmount(1100L);
        String url = "/account/recharge";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<Object> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<Object>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
    }

    @Test
    public void testWithdraw() throws Exception {
        WithdrawReq req = new WithdrawReq();
        req.setUserId(userId1);
        req.setTransId(String.valueOf(SnowflakeUtil.nextId()));
        req.setAmount(100L);
        String url = "/account/withdraw";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<Object> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<Object>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
    }

    @Test
    public void testFrozen() throws Exception {
        FrozenReq req = new FrozenReq();
        req.setUserId(userId2);
        req.setAccountType(AccountTypeEnums.NORMAL.getCode());
        req.setTransId(String.valueOf(SnowflakeUtil.nextId()));
        req.setAmount(100L);
        String url = "/account/frozen";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<Object> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<Object>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
    }

    @Test
    public void testUnfrozen() throws Exception {
        UnfrozenReq req = new UnfrozenReq();
        req.setUserId(userId1);
        req.setAccountType(AccountTypeEnums.NORMAL.getCode());
        req.setTransId(String.valueOf(SnowflakeUtil.nextId()));
        req.setAmount(100L);
        String url = "/account/unfrozen";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<Object> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<Object>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
    }

    @Test
    public void testTransaction() throws Exception {
        TransactionReq req = new TransactionReq();
        req.setUserId(userId1);
        req.setAccountType(AccountTypeEnums.NORMAL.getCode());
        req.setToUserId(userId2);
        req.setToAccountType(AccountTypeEnums.NORMAL.getCode());
//        req.setTransId(String.valueOf(SnowflakeUtil.nextId()));
        req.setTransId("1709977228283904");
        req.setAmount(100L);
        String url = "/account/transaction";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<Object> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<Object>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
    }

    @Test
    public void testDeleteAccount() throws Exception {
        deleteAccount(userId1);
        deleteAccount(userId2);
    }

    private void deleteAccount(String userId) throws Exception {
        DeleteAccountReq req = new DeleteAccountReq();
        req.setUserId(userId);
        req.setAccountType(AccountTypeEnums.NORMAL.getCode());
        req.setTransId(String.valueOf(SnowflakeUtil.nextId()));
        String url = "/account/delete";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<Object> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<Object>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
        Thread.sleep(5 * 1000);
    }


}
