package com.bitoasis.cryptocurrencyservice.api.facade;

import com.bitoasis.cryptocurrencyservice.api.boundary.helper.dto.CoinDTO;
import com.bitoasis.cryptocurrencyservice.api.boundary.helper.dto.CryptoDataDTO;
import com.bitoasis.cryptocurrencyservice.api.boundary.helper.dto.OrderByEnum;
import com.bitoasis.cryptocurrencyservice.api.boundary.helper.dto.UserDTO;
import com.bitoasis.cryptocurrencyservice.api.control.CryptoService;
import com.bitoasis.cryptocurrencyservice.api.control.integration.bo.TickerData;
import com.bitoasis.cryptocurrencyservice.api.entity.User;
import com.bitoasis.cryptocurrencyservice.api.entity.repository.CryptoDataRedisRepository;
import com.bitoasis.cryptocurrencyservice.api.entity.repository.UserRepository;
import com.bitoasis.cryptocurrencyservice.api.control.execption.CryptoServiceException;
import com.bitoasis.cryptocurrencyservice.api.control.integration.bo.CoinData;
import com.bitoasis.cryptocurrencyservice.api.control.integration.bo.CryptoData;
import com.bitoasis.cryptocurrencyservice.api.control.integration.bo.MetaData;
import com.bitoasis.cryptocurrencyservice.api.control.integration.resource.CryptoClient;
import com.bitoasis.cryptocurrencyservice.api.boundary.mapper.CryptoDataMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CryptoServiceTest {

    @InjectMocks
    private CryptoService cryptoService;

    @Mock
    private CryptoClient cryptoClient;

    @Mock
    private CryptoDataRedisRepository cryptoDataRedisRepository;

    @Mock
    private CryptoDataMapper cryptoDataMapper;

    @Mock
    private UserRepository userRepository;

    @Test
    void initializeApplicationSuccess() {
        CoinData coinData = buildCoinData();
        lenient().when(cryptoClient.listAllCryptoCurrency()).thenReturn(coinData);
        lenient().when(cryptoDataRedisRepository.save(coinData)).thenReturn(coinData);
        CoinData initCryptoData = cryptoService.initCryptoData();

        assertNotNull(initCryptoData);
        assertEquals(coinData.getId(),initCryptoData.getId());
    }

    @Test
    void retrieveCodeData_ServiceUnAvaliable() {
        CoinData coinData = buildCoinData();
        lenient().when(cryptoDataRedisRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(coinData));
        assertThrows(CryptoServiceException.class,
                () ->   cryptoService.retrieveCoinDate("C2"));  ;
    }
    @Test
    void retrieveCodeDataAvaliablee() {
        CoinData coinData = buildCoinData();
        lenient().when(cryptoDataRedisRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(coinData));
        lenient().when(cryptoDataMapper.toDTO(any())).thenReturn(new CryptoDataDTO());
        lenient().when(cryptoClient.tickerInfo(any())).thenReturn(buildTickerData());

        CryptoDataDTO c2 = cryptoService.retrieveCoinDate("C1");
        assertNotNull(c2);
    }
    @Test
    void retrieveAllCoinsSuccess() {
        CoinData coinData = buildCoinData();
        lenient().when(cryptoDataRedisRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(coinData));
        cryptoService.retrieveAllCoins(OrderByEnum.ASC);
        assertNotNull(coinData);
    }

    @Test
    void retrieveAllCoinsAfterInvalidateRedis() {
        CoinData coinData = buildCoinData();
        CoinData savedOne=null;
        lenient().when(cryptoDataRedisRepository.findTopByOrderByIdDesc()).thenReturn(Optional.ofNullable(savedOne));
        lenient().when(cryptoClient.listAllCryptoCurrency()).thenReturn(coinData);
        lenient().when(cryptoDataRedisRepository.save(coinData)).thenReturn(coinData);
        List<CoinDTO> coinDTOList = cryptoService.retrieveAllCoins(OrderByEnum.ASC);
        assertNotNull(coinDTOList);
    }

    @Test
    void registerUser() {
        User user = buildUser();
        UserDTO userDTO = buildUserDto();
        User alreadyExistOne=null;
        lenient().when(userRepository.findByName("bb")).thenReturn(Optional.ofNullable(alreadyExistOne));
        lenient().when(userRepository.save(any())).thenReturn(user);

        User savedUser= cryptoService.registerUser(userDTO);
        assertNotNull(savedUser);

    }
    @Test
    void registerUserAlreadyExist() {
        User user = buildUser();
        UserDTO userDTO = buildUserDto();
        User alreadyExistOne=buildUser();
        lenient().when(userRepository.findByName(any())).thenReturn(Optional.of(alreadyExistOne));
        lenient().when(userRepository.save(any())).thenReturn(user);
        assertThrows(CryptoServiceException.class,
                () -> cryptoService.registerUser(userDTO));

    }
    private User buildUser() {
        User user=new User();
        user.setName("aa");
        user.setId(1);
        user.setEmail("aaa");
        user.setRoles("ADMIN");
        return user;
    }
    private UserDTO buildUserDto(){
        UserDTO userDTO=new UserDTO();
        userDTO.setName("ahmed");
        userDTO.setEmail("ahmed.labeb@gmail.com");
        userDTO.setPassword("123");
        return userDTO;
    }
    private CoinData buildCoinData() {
        CoinData coinData = new CoinData();
        coinData.setId(0);
        List<CryptoData> cryptoDataList = new ArrayList();
        cryptoDataList.add(buildCryptoData());
        cryptoDataList.add(buildCryptoData());
        coinData.setCryptoDataList(cryptoDataList);
        coinData.setMetaData(buildMetadata());
        return coinData;
    }

    private CryptoData buildCryptoData() {
        CryptoData cryptoData = new CryptoData();
        cryptoData.setId("1");
        cryptoData.setName("c1");
        cryptoData.setSymbol("C1");
        return cryptoData;
    }

    private MetaData buildMetadata() {
        MetaData metaData=new MetaData();
        metaData.setTimestamp("2345678");
        metaData.setNumCryptocurrencies("2");
        return metaData;
    }

    private TickerData buildTickerData(){
        TickerData tickerData=new TickerData();
        tickerData.setMetaData(buildMetadata());
        HashMap<Long, CryptoData> cryptoDataHashMap = new HashMap<>();
        cryptoDataHashMap.put(1L,buildCryptoData());
        tickerData.setCryptoDataMap(cryptoDataHashMap);
        return tickerData;
    }
}
