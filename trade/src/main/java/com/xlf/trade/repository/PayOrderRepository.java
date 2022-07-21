package com.xlf.trade.repository;

import com.xlf.trade.entity.PayOrderDo;
import com.xlf.trade.entity.PayOrderDoExample;
import com.xlf.trade.enums.PayOrderStatusEnums;
import com.xlf.trade.repository.mapper.PayOrderMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class PayOrderRepository {
    @Resource
    private PayOrderMapper payOrderMapper;

    public void insert(PayOrderDo payOrderDo) {
        payOrderMapper.insertSelective(payOrderDo);
    }

    public boolean beforeCallingPayChannel(Long orderId, String requestUrl, String requestParam) {
        PayOrderDo payOrderDo = new PayOrderDo();
        payOrderDo.setStatus(PayOrderStatusEnums.CALLING_PAY_CHANNEL.getCode());
        payOrderDo.setRequestUrl(requestUrl);
        payOrderDo.setRequestParam(requestParam);

        PayOrderDoExample example = new PayOrderDoExample();
        example.createCriteria().andOrderIdEqualTo(orderId).andStatusEqualTo(PayOrderStatusEnums.INIT.getCode());
        return payOrderMapper.updateByExampleSelective(payOrderDo, example) == 1;
    }

    public PayOrderDo queryPayOrder(Long orderId) {
        return payOrderMapper.selectByPrimaryKey(orderId);
    }

    public boolean afterCalling(Long orderId, String response, Integer fromStatus, Integer toStatus, String remark) {
        PayOrderDo payOrderDo = new PayOrderDo();
        payOrderDo.setStatus(toStatus);
        if (StringUtils.isNotBlank(response)) {
            payOrderDo.setResponse(response);
        }
        if (StringUtils.isNotBlank(remark)) {
            payOrderDo.setRemark(remark);
        }
        PayOrderDoExample example = new PayOrderDoExample();
        example.createCriteria().andOrderIdEqualTo(orderId)
                .andStatusEqualTo(fromStatus);
        return payOrderMapper.updateByExampleSelective(payOrderDo, example) == 1;
    }

}
