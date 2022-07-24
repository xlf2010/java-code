package com.xlf.trade.remote.third;

import com.xlf.trade.entity.PayOrderDo;
import com.xlf.trade.remote.third.wechat.vo.*;
import com.xlf.trade.vo.request.*;

public interface ThirdRemoteService {
    WechatCreateAccountResult createAccount(CreateTradeAccountReq req, PayOrderDo payOrderDo);

    WechatRechargeResult recharge(TradeRechargeReq req, PayOrderDo payOrderDo);

    WechatWithdrawResult withdraw(TradeWithdrawReq req, PayOrderDo payOrderDo);

    WechatTransactionResult transaction(TradeTransactionReq req, PayOrderDo payOrderDo);

    WechatFrozenResult frozen(TradeFrozenReq req, PayOrderDo payOrderDo);

    WechatUnfrozenResult unfrozen(TradeUnfrozenReq req, PayOrderDo payOrderDo);

}
