package com.xlf.trade.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xlf.trade.BaseTest;
import com.xlf.trade.entity.AccountFlowDo;
import com.xlf.trade.entity.AccountInfoDo;
import com.xlf.trade.enums.AccountCreateTypeEnums;
import com.xlf.trade.enums.AccountFlowOperateTypeEnums;
import com.xlf.trade.enums.AccountTypeEnums;
import com.xlf.trade.enums.CurrencyTypeEnums;
import com.xlf.trade.repository.AccountFlowRepository;
import com.xlf.trade.repository.AccountInfoRepository;
import com.xlf.trade.vo.request.*;
import com.xlf.trade.vo.response.CreateAccountRsp;
import com.xlf.common.response.ApiResult;
import com.xlf.common.util.JsonUtil;
import com.xlf.common.util.SnowflakeUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

public class AccountControllerTest extends BaseTest {

    @Resource
    private AccountInfoRepository accountInfoRepository;
    @Resource
    private AccountFlowRepository accountFlowRepository;

    AccountTypeEnums accountTypeEnums = AccountTypeEnums.WECHAT_PAY;

    @Test
    public void testCreateAccount() throws Exception {

        deleteAccount(userId1, accountTypeEnums);
        deleteAccount(userId2, accountTypeEnums);

        createAccount(userId1, accountTypeEnums);
        createAccount(userId2, accountTypeEnums);

        List<AccountInfoDo> accountInfoDos = accountInfoRepository.queryByUserIdAccountType(userId1, accountTypeEnums.getCode());
        Assert.notEmpty(accountInfoDos, String.format("userID:%s,trade type:%s can't be null", userId1, accountTypeEnums));
        accountInfoDos = accountInfoRepository.queryByUserIdAccountType(userId2, accountTypeEnums.getCode());
        Assert.notEmpty(accountInfoDos, String.format("userID:%s,trade type:%s can't be null", userId2, accountTypeEnums));

    }

    private void createAccount(String userId, AccountTypeEnums accountTypeEnums) throws Exception {
        CreateAccountReq req = new CreateAccountReq();
        req.setTransId(String.valueOf(SnowflakeUtil.nextId()));
        req.setUserId(userId);
        req.setAccountType(accountTypeEnums.getCode());
        req.setAccountName("name" + System.currentTimeMillis());
        req.setUserName("test user name");
        req.setCreateBy(req.getUserId());
        req.setCreateType(AccountCreateTypeEnums.USER.getCode());
        req.setCurrencyType(CurrencyTypeEnums.CNY.getCode());
        String url = "/trade/create";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<CreateAccountRsp> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<CreateAccountRsp>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
        Assert.notNull(rsp.getData(), "rsp.data can't be null");
        Assert.isTrue(StringUtils.isNotBlank(rsp.getData().getAccountId()), "rsp.data.accountId can't be null");

    }

    @Test
    public void testRecharge() throws Exception {
        AccountInfoDo before = getAccountInfoDo(userId1);

        long amount = 1100L;

        RechargeReq req = new RechargeReq();
        req.setUserId(userId1);
        req.setAccountType(accountTypeEnums.getCode());
        req.setTransId(String.valueOf(SnowflakeUtil.nextId()));
        req.setAmount(amount);
        String url = "/trade/recharge";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<Object> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<Object>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");

        checkAccountAndFlow(before, userId1, req.getTransId(), AccountFlowOperateTypeEnums.RECHARGE, amount, 0);

    }

    @Test
    public void testWithdraw() throws Exception {
        AccountInfoDo before = getAccountInfoDo(userId1);
        long amount = 100L;

        WithdrawReq req = new WithdrawReq();
        req.setUserId(userId1);
        req.setTransId(String.valueOf(SnowflakeUtil.nextId()));
        req.setAccountType(AccountTypeEnums.WECHAT_PAY.getCode());
        req.setAmount(amount);
        String url = "/trade/withdraw";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<Object> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<Object>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");

        checkAccountAndFlow(before, userId1, req.getTransId(), AccountFlowOperateTypeEnums.WITHDRAW, -amount, 0);
    }


    @Test
    public void testFrozen() throws Exception {
        AccountInfoDo before = getAccountInfoDo(userId1);

        long amount = 100L;
        FrozenReq req = new FrozenReq();
        req.setUserId(userId1);
        req.setAccountType(AccountTypeEnums.WECHAT_PAY.getCode());
        req.setTransId(String.valueOf(SnowflakeUtil.nextId()));
        req.setAmount(amount);
        String url = "/trade/frozen";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<Object> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<Object>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
        checkAccountAndFlow(before, userId1, req.getTransId(), AccountFlowOperateTypeEnums.FROZEN, -amount, amount);

    }

    @Test
    public void testUnfrozen() throws Exception {
        AccountInfoDo before = getAccountInfoDo(userId1);

        long amount = 100L;
        UnfrozenReq req = new UnfrozenReq();
        req.setUserId(userId1);
        req.setAccountType(AccountTypeEnums.WECHAT_PAY.getCode());
        req.setTransId(String.valueOf(SnowflakeUtil.nextId()));
        req.setAmount(100L);
        String url = "/trade/unfrozen";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<Object> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<Object>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
        checkAccountAndFlow(before, userId1, req.getTransId(), AccountFlowOperateTypeEnums.UNFROZEN, amount, -amount);

    }

    @Test
    public void testTransaction() throws Exception {
        AccountInfoDo before1 = getAccountInfoDo(userId1);
        AccountInfoDo before2 = getAccountInfoDo(userId2);
        long amount = 100L;

        TransactionReq req = new TransactionReq();
        req.setUserId(userId1);
        req.setAccountType(AccountTypeEnums.WECHAT_PAY.getCode());
        req.setToUserId(userId2);
        req.setToAccountType(AccountTypeEnums.WECHAT_PAY.getCode());
//        req.setTransId(String.valueOf(SnowflakeUtil.nextId()));
        req.setTransId("1709977228283904");
        req.setAmount(amount);
        String url = "/trade/transaction";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<Object> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<Object>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");

        checkAccountAndFlow(before1, userId1, req.getTransId(), AccountFlowOperateTypeEnums.TRANSACTION_OUT, -amount, 0);
        checkAccountAndFlow(before2, userId2, req.getTransId(), AccountFlowOperateTypeEnums.TRANSACTION_IN, amount, 0);

    }


    private void checkAccountAndFlow(AccountInfoDo before, String userId, String transId, AccountFlowOperateTypeEnums accountFlowOperateTypeEnums, long changeAmount, long changeFrozen) {
        AccountInfoDo after = getAccountInfoDo(userId);
        Assert.isTrue(after.getBalance() == before.getBalance() + changeAmount);
        Assert.isTrue(after.getFrozenBalance() == before.getFrozenBalance() + changeFrozen);

        List<AccountFlowDo> accountFlowDos = accountFlowRepository.queryByTransIdOperateType(transId, accountFlowOperateTypeEnums.getCode());
        Assert.notEmpty(accountFlowDos);
        AccountFlowDo accountFlowDo = accountFlowDos.get(0);
        Assert.isTrue(accountFlowDo.getAmount() == Math.abs(changeAmount));
        Assert.isTrue(accountFlowDo.getBalance().equals(after.getBalance()));
        Assert.isTrue(accountFlowDo.getFrozenBalance().equals(after.getFrozenBalance()));
    }

    private AccountInfoDo getAccountInfoDo(String userId) {
        List<AccountInfoDo> accountInfoDos = accountInfoRepository.queryByUserIdAccountType(userId, accountTypeEnums.getCode());
        Assert.notEmpty(accountInfoDos, String.format("userID:%s,trade type:%s can't be null", userId, accountTypeEnums));

        return accountInfoDos.get(0);
    }


    @Test
    public void testDeleteAccount() throws Exception {

        deleteAccount(userId1, accountTypeEnums);
        deleteAccount(userId2, accountTypeEnums);

        List<AccountInfoDo> accountInfoDos = accountInfoRepository.queryByUserIdAccountType(userId1, accountTypeEnums.getCode());
        Assert.isTrue(CollectionUtils.isEmpty(accountInfoDos), String.format("userID:%s,trade type:%s can't be null", userId1, accountTypeEnums));
        accountInfoDos = accountInfoRepository.queryByUserIdAccountType(userId2, accountTypeEnums.getCode());
        Assert.isTrue(CollectionUtils.isEmpty(accountInfoDos), String.format("userID:%s,trade type:%s can't be null", userId2, accountTypeEnums));

    }

    private void deleteAccount(String userId, AccountTypeEnums accountTypeEnums) throws Exception {
        DeleteAccountReq req = new DeleteAccountReq();
        req.setUserId(userId);
        req.setAccountType(accountTypeEnums.getCode());
        req.setTransId(String.valueOf(SnowflakeUtil.nextId()));
        String url = "/trade/delete";
        String json = postJson(url, JsonUtil.toJsonString(req));

        ApiResult<Object> rsp = JsonUtil.fromJson(json, new TypeReference<ApiResult<Object>>() {
        });
        Assert.notNull(rsp, "rsp can't be null");
        Thread.sleep(5 * 1000);
    }


}
