package com.bitoasis.cryptocurrencyservice.api.entity.repository;

import com.bitoasis.cryptocurrencyservice.api.control.integration.bo.CoinData;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;


public interface CryptoDataRedisRepository extends CrudRepository<CoinData, Integer> {


    Optional<CoinData> findTopByOrderByIdDesc();
}
