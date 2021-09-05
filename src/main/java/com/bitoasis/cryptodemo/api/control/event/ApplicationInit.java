package com.bitoasis.cryptodemo.api.control.event;

import com.bitoasis.cryptodemo.api.control.CryptoService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInit {

    private CryptoService cryptoService;

    public ApplicationInit(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        cryptoService.initCryptoData();
    }

}
