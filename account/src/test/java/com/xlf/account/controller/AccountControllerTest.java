package com.xlf.account.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xlf.account.BaseTest;
import com.xlf.account.enums.AccountCreateTypeEnums;
import com.xlf.account.enums.AccountTypeEnums;
import com.xlf.account.vo.request.CreateAccountReq;
import com.xlf.account.vo.response.CreateAccountRsp;
import com.xlf.common.response.ApiResult;
import com.xlf.common.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.Assert;

public class AccountControllerTest extends BaseTest {

    @Test
    public void testCreateAccount() throws Exception {
        CreateAccountReq req = new CreateAccountReq();
        req.setUserId("U" + System.currentTimeMillis());
        req.setAccountType(AccountTypeEnums.NORMAL.getCode());
        req.setAccountName("name" + System.currentTimeMillis());
        req.setUserName("test user name");
        req.setCreateBy(req.getUserId());
        req.setCreateType(AccountCreateTypeEnums.USER.getCode());
        String url = "/account/create";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<CreateAccountRsp> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<CreateAccountRsp>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
        Assert.notNull(rsp.getData(), "rsp.data can't be null");
        Assert.isTrue(StringUtils.isNotBlank(rsp.getData().getAccountId()), "rsp.data.accountId can't be null");

    }
}
