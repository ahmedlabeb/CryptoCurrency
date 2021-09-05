package com.bitoasis.cryptodemo.api.control.integration.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CryptoData implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    @Id
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("website_slug")
    private String websiteSlug;

    @JsonProperty("rank")
    private String rank;

    @JsonProperty("circulating_supply")
    private Long circulatingSupply;

    @JsonProperty("total_supply")
    private Long totalSupply;

    @JsonProperty("max_supply")
    private Long maxSupply;

    @JsonProperty("quotes")
    private Map<CurrencyEnum,CurrencyDetails> quotes;

    @JsonProperty("last_updated")
    private Long lastUpdated;

}
