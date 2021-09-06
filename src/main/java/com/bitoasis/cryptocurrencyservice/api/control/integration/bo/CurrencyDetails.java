package com.bitoasis.cryptocurrencyservice.api.control.integration.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class CurrencyDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("volume_24h")
    private Long volume24H;

    @JsonProperty("market_cap")
    private Long marketCap;

    @JsonProperty("percentage_change_1h")
    private BigDecimal percentageChange1H;

    @JsonProperty("percentage_change_24h")
    private BigDecimal percentageChange24H;


    @JsonProperty("percentage_change_7d")
    private BigDecimal percentageChange7D;

    @JsonProperty("percent_change_1h")
    private BigDecimal percentChange1H;

    @JsonProperty("percent_change_24h")
    private BigDecimal percentChange24H;

    @JsonProperty("percent_change_7d")
    private BigDecimal percentChange7D;
}
