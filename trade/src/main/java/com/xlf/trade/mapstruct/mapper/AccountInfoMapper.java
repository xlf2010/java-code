package com.xlf.trade.mapstruct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountInfoMapper {

    AccountInfoMapper INSTANCE = Mappers.getMapper(AccountInfoMapper.class);


}
