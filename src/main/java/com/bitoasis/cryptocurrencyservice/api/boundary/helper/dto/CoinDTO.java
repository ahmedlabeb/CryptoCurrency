package com.bitoasis.cryptocurrencyservice.api.boundary.helper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CoinDTO implements Comparable<CoinDTO>{
    private String code;

    private String name;

    @Override
    public int compareTo(CoinDTO o) {
        return name.compareTo(o.getName());
    }
}
