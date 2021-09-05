package com.bitoasis.cryptodemo.api.control.integration.resource;

import com.bitoasis.cryptodemo.api.control.integration.bo.CoinData;
import com.bitoasis.cryptodemo.api.control.integration.bo.TickerData;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "crypto-client", url = "${crypto.api.url}")
@Headers("Content-Type: application/json")
public interface CryptoClient {

    @RequestMapping(value = "listings/", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
    CoinData listAllCryptoCurrency();

    @RequestMapping(value = "/ticker/{id}/", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
    TickerData tickerInfo(@PathVariable("id") final Long coinId);

}
