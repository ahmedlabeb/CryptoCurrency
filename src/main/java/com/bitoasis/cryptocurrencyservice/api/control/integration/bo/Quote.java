package com.bitoasis.cryptocurrencyservice.api.control.integration.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Quote implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("USD")
    private CurrencyDetails currencyDetails;
}
