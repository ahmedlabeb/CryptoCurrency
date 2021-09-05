package com.bitoasis.cryptodemo.api.control;

import com.bitoasis.cryptodemo.api.boundary.helper.dto.CoinDTO;
import com.bitoasis.cryptodemo.api.boundary.helper.dto.CryptoDataDTO;
import com.bitoasis.cryptodemo.api.boundary.helper.dto.OrderByEnum;
import com.bitoasis.cryptodemo.api.boundary.helper.dto.UserDTO;
import com.bitoasis.cryptodemo.api.control.execption.CryptoServiceError;
import com.bitoasis.cryptodemo.api.control.integration.bo.CoinData;
import com.bitoasis.cryptodemo.api.control.integration.bo.CryptoData;
import com.bitoasis.cryptodemo.api.control.integration.bo.TickerData;
import com.bitoasis.cryptodemo.api.control.integration.resource.CryptoClient;
import com.bitoasis.cryptodemo.api.boundary.mapper.CryptoDataMapper;
import com.bitoasis.cryptodemo.api.entity.User;
import com.bitoasis.cryptodemo.api.entity.repository.CryptoDataRedisRepository;
import com.bitoasis.cryptodemo.api.entity.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CryptoService {

    private CryptoClient cryptoClient;

    private CryptoDataRedisRepository cryptoDataRedisRepository;

    private CryptoDataMapper cryptoDataMapper;

    private UserRepository userRepository;

    public CryptoService(CryptoClient cryptoClient, CryptoDataRedisRepository cryptoDataRedisRepository,
                         CryptoDataMapper cryptoDataMapper, UserRepository userRepository) {
        this.cryptoClient = cryptoClient;
        this.cryptoDataRedisRepository = cryptoDataRedisRepository;
        this.cryptoDataMapper = cryptoDataMapper;
        this.userRepository = userRepository;
    }

    public CoinData initCryptoData() {
        CoinData coinData = cryptoClient.listAllCryptoCurrency();
        return cryptoDataRedisRepository.save(coinData);
    }

    /**
     * retirive all coins that saved in DB in specific order given to the method
     *
     * @param orderByEnum
     * @return
     */
    public List<CoinDTO> retrieveAllCoins(OrderByEnum orderByEnum) {
        Optional<CoinData> optionalCoinData = cryptoDataRedisRepository.findTopByOrderByIdDesc();
        CoinData coinData = validateOnCoinAvalability(optionalCoinData);
        List<CoinDTO> coinDTOList = coinData
                .getCryptoDataList()
                .stream()
                .map(cryptoData -> new CoinDTO(cryptoData.getSymbol(), cryptoData.getName()))
                .sorted()
                .collect(Collectors.toList());

        if (orderByEnum.value().equals(OrderByEnum.DESC.value()))
            coinDTOList.sort(Comparator.comparing(CoinDTO::getName).reversed());

        return coinDTOList;

    }

    private CoinData validateOnCoinAvalability(Optional<CoinData> optionalCoinData) {
        CoinData coinData=null;
        if(optionalCoinData.isEmpty()) {
            coinData = initCryptoData();
        }else{
            coinData= optionalCoinData.get();
        }
        return coinData;
    }

    public CryptoDataDTO retrieveCoinDate(String coinCode) {
        Optional<CoinData> optionalCoinData = cryptoDataRedisRepository.findTopByOrderByIdDesc();
        CoinData coinData=validateOnCoinAvalability(optionalCoinData);
        Optional<CryptoData> optionalCryptoData = coinData.getCryptoDataList()
                .stream().filter(cryptoData -> cryptoData.getSymbol().equals(coinCode)).findFirst();
        if (optionalCryptoData.isEmpty())
            CryptoServiceError.SERVICE_UN_AVAILABLE.buildExcpetion();

        Long coinId= Long.parseLong(optionalCryptoData.get().getId());
        TickerData retrivedTickerData = cryptoClient.tickerInfo(coinId);

        CryptoDataDTO cryptoDataDTO = cryptoDataMapper.toDTO(retrivedTickerData.getCryptoDataMap().get(coinId));
        return cryptoDataDTO;
    }

    public User registerUser(UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findByName(userDTO.getName());
        if (optionalUser.isPresent()) {
            throw CryptoServiceError.USER_ALREADY_EXIST.buildExcpetion();
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setName(userDTO.getName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setRoles("ROLE_ADMIN");

        return userRepository.save(user);
    }
}
