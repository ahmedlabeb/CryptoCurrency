package com.bitoasis.cryptodemo.api;

import com.bitoasis.cryptodemo.api.boundary.helper.dto.CoinDTO;
import com.bitoasis.cryptodemo.api.boundary.helper.dto.CryptoDataDTO;
import com.bitoasis.cryptodemo.api.boundary.helper.dto.OrderByEnum;
import com.bitoasis.cryptodemo.api.boundary.helper.dto.UserDTO;
import com.bitoasis.cryptodemo.api.entity.User;
import com.bitoasis.cryptodemo.api.control.CryptoService;
import com.bitoasis.cryptodemo.api.boundary.helper.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "Crypto EndPoints")
@Slf4j
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:9090"})
public class CryptoApi {

    private CryptoService cryptoService;

    public CryptoApi(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "Register User ", response = ResponseEntity.class)
    public ResponseEntity<?> register(@RequestBody @Valid @NotNull UserDTO userDTO) {
        log.info("User Registeration");
        User user = cryptoService.registerUser(userDTO);
        return ResponseUtil.wrapOrNotFound(user);
    }

    @RequestMapping(value = "/coins", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieve coins Details", response = ResponseEntity.class)
    public ResponseEntity<?> retrieveCoins(@RequestParam(value = "sort", required = false, defaultValue = "ASC") final OrderByEnum orderBy) {
        log.info("Retreive Coin Data ");
        final List<CoinDTO> coinDTOList = cryptoService.retrieveAllCoins(orderBy);
        return ResponseUtil.wrapOrNotFound(coinDTOList);
    }

    @RequestMapping(value = "/ticker/{coin_code}", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieve coins tickers by coin code ", response = ResponseEntity.class)
    public ResponseEntity<?> coinInfo(@PathVariable(value = "coin_code") final String coinCode) {
        log.info("Retreive TickerCoin Info ");
        CryptoDataDTO cryptoData = cryptoService.retrieveCoinDate(coinCode);
        return ResponseUtil.wrapOrNotFound(cryptoData);
    }
}
