package com.xlf.account.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xlf.account.BaseTest;
import com.xlf.account.common.response.AccountFlowRsp;
import com.xlf.account.common.response.QueryAccountInfoRsp;
import com.xlf.common.response.ApiResult;
import com.xlf.common.response.PageResponse;
import com.xlf.common.util.JsonUtil;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountQueryControllerTest extends BaseTest {

    @Test
    public void testQueryAccountInfo() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId1);

        String url = "/trade/query/account_info";
        String json = get(url, map);

        ApiResult<List<QueryAccountInfoRsp>> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<List<QueryAccountInfoRsp>>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
        Assert.notNull(rsp.getData(), "rsp.data can't be null");
    }

    @Test
    public void testQueryAccountFlow() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId1);

        String url = "/trade/query/account_flow";
        String json = get(url, map);

        ApiResult<PageResponse<AccountFlowRsp>> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<PageResponse<AccountFlowRsp>>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
        Assert.notNull(rsp.getData(), "rsp.data can't be null");


    }

    @Test
    public void testQueryAccountFlow1() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("transId", "1294478363332608");
        map.put("operateType", "4");

        String url = "/trade/query/account_flow_trans_operate";
        String json = get(url, map);

        ApiResult<List<AccountFlowRsp>> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<List<AccountFlowRsp>>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
        Assert.notNull(rsp.getData(), "rsp.data can't be null");


    }
}
