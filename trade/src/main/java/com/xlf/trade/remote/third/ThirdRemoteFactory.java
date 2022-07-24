package com.xlf.trade.remote.third;

import com.xlf.common.enums.PayChannelEnums;
import com.xlf.common.exception.BusinessException;
import com.xlf.common.exception.ErrorCodeEnum;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class ThirdRemoteFactory implements ApplicationContextAware {
    private Map<PayChannelEnums, ThirdRemoteService> serviceMap = new HashMap<>();
    private ApplicationContext applicationContext;

    @PostConstruct
    public void initService() {
        Map<String, ThirdRemoteService> thirdRemoteServiceMap = applicationContext.getBeansOfType(ThirdRemoteService.class);
        thirdRemoteServiceMap.forEach((k, v) -> {
            ThirdRemoteAnnotation thirdRemoteAnnotation = AnnotationUtils.findAnnotation(v.getClass(), ThirdRemoteAnnotation.class);
            if (Objects.nonNull(thirdRemoteAnnotation)) {
                for (PayChannelEnums payChannelEnums : thirdRemoteAnnotation.value()) {
                    serviceMap.put(payChannelEnums, v);
                }
            }
        });
        serviceMap = Collections.unmodifiableMap(serviceMap);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ThirdRemoteService getServiceByPayChannel(PayChannelEnums payChannelEnums) {
        ThirdRemoteService thirdRemoteService = serviceMap.get(payChannelEnums);
        if (Objects.isNull(thirdRemoteService)) {
            throw new BusinessException(ErrorCodeEnum.REQUEST_PARAMS_FAIL.getCode(), "can't find service to handle payChannel" + payChannelEnums.getCode());
        }
        return thirdRemoteService;
    }
}
