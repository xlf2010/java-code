package com.xlf.common.exception;

public enum ErrorCodeEnum {

    FAIL(-1, "FAIL"),

    SUCCESS(0, "success"),

    SERVICE_BUSY_ERROR(1000, "lock business,please try again later"),

    REQUEST_PARAMS_FAIL(1001, "params error"),

    USER_NOT_LOGIN(1002, "not login"),

    REQUEST_PARAMS_FORMAT_ERROR(1102, "params format error"),

    TOKEN_EXPIRED(2301, "token expire"),

    ACCOUNT_NOT_EXIST(3301, "account doesn't exist"),

    DATA_PROCESSING(3302, "data processing"),

    ACCOUNT_STATUS_ERROR(3303, "trade status error"),

    RECHARGE_ERROR(3303, "recharge error"),

    WITHDRAW_ERROR(3304, "withdraw error"),

    TRANSACTION_ERROR(3305, "transaction error"),

    ACCOUNT_HAS_BALANCE_ERROR(3306, "trade has balance"),

    BALANCE_NOT_ENOUGH_EXIST_ERROR(3307, "balance not enough"),

    FROZEN_BALANCE_NOT_ENOUGH_EXIST_ERROR(3308, "frozen balance not enough"),

    FROZEN_ERROR(3309, "frozen error"),

    UNFROZEN_ERROR(3310, "unfrozen error"),

    ACCOUNT_ERROR(3400, "query account error"),

    PAY_ERROR(3500, "pay error"),

    NETWORK_ERROR(3600, "network error"),
    ;

    private final int code;
    private final String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public BusinessException newException() {
        return new BusinessException(code, msg);
    }
}