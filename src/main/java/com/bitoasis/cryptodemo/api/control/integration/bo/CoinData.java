package com.bitoasis.cryptodemo.api.control.integration.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@RedisHash(value = "coinData",timeToLive=600L)
public class CoinData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id=0;

    @JsonProperty("data")
    private List<CryptoData> cryptoDataList;

    @JsonProperty("metadata")
    private MetaData metaData;
}
