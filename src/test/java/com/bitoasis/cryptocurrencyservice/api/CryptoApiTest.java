package com.bitoasis.cryptocurrencyservice.api;

import com.bitoasis.cryptocurrencyservice.api.CryptoApi;
import com.bitoasis.cryptocurrencyservice.api.boundary.helper.dto.CryptoDataDTO;
import com.bitoasis.cryptocurrencyservice.api.boundary.helper.dto.UserDTO;
import com.bitoasis.cryptocurrencyservice.api.control.CryptoService;
import com.bitoasis.cryptocurrencyservice.api.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CryptoApiTest {
    @MockBean
    CryptoService cryptoService;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    Logger log;

    @InjectMocks
    CryptoApi cryptoApi;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void testRegister() throws Exception {
        lenient().when(cryptoService.registerUser(any())).thenReturn(buildUser());
        mockMvc.perform(post("/api/register")
                .content(mapper.writeValueAsString(buildUserDto()))
                .contentType(APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }


    @Test
    void testRegisterWithoutBody() throws Exception {
        mockMvc.perform(post("/api/register")
                .content(mapper.writeValueAsString(buildUserDtoWithoutFields()))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCoins() throws Exception {
        mockMvc.perform(get("/api/coins")
                .contentType(APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void testCoinInfoWithCredentials() throws Exception {
        lenient().when(cryptoService.retrieveCoinDate(any())).thenReturn(buildCryptoDto());
        mockMvc.perform(get("/api/ticker/1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }


    private UserDTO buildUserDtoWithoutFields(){
        UserDTO userDTO=new UserDTO();
        return userDTO;
    }
    private UserDTO buildUserDto(){
        UserDTO userDTO=new UserDTO();
        userDTO.setName("ahmed");
        userDTO.setEmail("ahmed.labeb@gmail.com");
        userDTO.setPassword("123");
        return userDTO;
    }
    private CryptoDataDTO buildCryptoDto(){
        CryptoDataDTO cryptoDataDTO=new CryptoDataDTO();
        cryptoDataDTO.setCode("ABC");
        cryptoDataDTO.setPrice(1.2D);
        cryptoDataDTO.setDailyChange(new BigDecimal(1.3434));
        cryptoDataDTO.setVolume(123L);
        cryptoDataDTO.setLastUpdated(23456L);
        return cryptoDataDTO;
    }
    private User buildUser(){
        User user=new User();
        user.setName("ahmed");
        user.setEmail("ahmed.labeb@gmail.com");
        user.setPassword("123");
        return user;
    }
}

