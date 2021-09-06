package com.bitoasis.cryptocurrencyservice.api.boundary.helper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CryptoDataDTO {

    @JsonProperty("code")
    private String code;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("volume")
    private Long volume;

    @JsonProperty("dailyChange")
    private BigDecimal dailyChange;

    @JsonProperty("lastUpdated")
    private Long lastUpdated;
}
