package com.bitoasis.cryptodemo.api.control.integration.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Map;


@Getter
@Setter
@RedisHash(value = "tickerData",timeToLive=300L)
public class TickerData implements Serializable {
    private static final long serialVersionUID = 2L;

    @JsonProperty("data")
    private Map<Long,CryptoData> cryptoDataMap;

    @JsonProperty("metadata")
    private MetaData metaData;
}
