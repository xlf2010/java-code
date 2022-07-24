package com.xlf.trade.remote.third.wechat.vo;

import lombok.Data;

@Data
public class WechatBaseResult {
    public static final int SUCCESS_CODE = 200;

    private int code;
    private String msg;
}
