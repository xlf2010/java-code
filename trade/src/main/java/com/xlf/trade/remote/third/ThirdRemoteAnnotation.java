package com.xlf.trade.remote.third;

import com.xlf.common.enums.PayChannelEnums;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ThirdRemoteAnnotation {
    PayChannelEnums[] value();
}
