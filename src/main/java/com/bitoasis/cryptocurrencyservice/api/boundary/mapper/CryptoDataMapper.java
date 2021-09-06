package com.bitoasis.cryptocurrencyservice.api.boundary.mapper;

import com.bitoasis.cryptocurrencyservice.api.boundary.helper.dto.CryptoDataDTO;
import com.bitoasis.cryptocurrencyservice.api.control.integration.bo.CryptoData;
import com.bitoasis.cryptocurrencyservice.api.control.integration.bo.CurrencyEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring",imports = CurrencyEnum.class)
public interface CryptoDataMapper {
	@Mappings({
			@Mapping(target = "code", source = "entity.symbol"),
			@Mapping(target = "price", expression = "java(entity.getQuotes().get(CurrencyEnum.USD).getPrice())"),
			@Mapping(target = "volume",expression = "java(entity.getQuotes().get(CurrencyEnum.USD).getVolume24H())"),
			@Mapping(target = "dailyChange",expression = "java(entity.getQuotes().get(CurrencyEnum.USD).getPercentageChange24H())"),
			@Mapping(target = "lastUpdated", source = "entity.lastUpdated")})
    CryptoDataDTO toDTO(CryptoData entity);
}
