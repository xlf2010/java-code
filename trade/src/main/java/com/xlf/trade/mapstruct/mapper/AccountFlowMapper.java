package com.xlf.trade.mapstruct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountFlowMapper {

    AccountFlowMapper INSTANCE = Mappers.getMapper(AccountFlowMapper.class);

}
