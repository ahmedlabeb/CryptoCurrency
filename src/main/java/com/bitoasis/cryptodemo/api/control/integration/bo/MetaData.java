package com.bitoasis.cryptodemo.api.control.integration.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MetaData implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("num_cryptocurrencies")
    private String numCryptocurrencies;

    @JsonProperty("error")
    private String error;
}
