package com.bitoasis.cryptodemo.api.entity.repository;

import com.bitoasis.cryptodemo.api.control.integration.bo.CoinData;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;


public interface CryptoDataRedisRepository extends CrudRepository<CoinData, Integer> {


    Optional<CoinData> findTopByOrderByIdDesc();
}
