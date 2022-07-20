package com.coralogix.calculator.mappers;

import com.coralogix.calculator.entities.ApiResponse;
import com.coralogix.calculator.entities.ExchangeRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ApiMapper {

    @Mappings({@Mapping(target = "originCurrency", source = "apiResponse.base")})
    ExchangeRate simpleMapper(ApiResponse apiResponse);

}
