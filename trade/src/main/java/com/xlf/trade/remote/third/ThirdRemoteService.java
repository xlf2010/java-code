package com.xlf.trade.remote.third;

import com.xlf.trade.entity.PayOrderDo;
import com.xlf.trade.vo.request.CreateTradeAccountReq;
import com.xlf.trade.vo.request.TradeRechargeReq;

public interface ThirdRemoteService {
    <T> T createAccount(CreateTradeAccountReq req, PayOrderDo payOrderDo,Class<T> resultClass);

    <T> T recharge(TradeRechargeReq req, PayOrderDo payOrderDo, Class<T> resultClass);
}
