package com.xlf.trade.vo.request;

import com.xlf.common.enums.PayChannelEnums;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DeleteTradeAccountReq {
    @NotBlank(message = "userId can't be blank")
    private String userId;
    /**
     * payChannel Enum
     *
     * @see PayChannelEnums
     */
    @NotNull(message = "payChannel can't be null")
    private Integer payChannel;

}
