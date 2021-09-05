package com.bitoasis.cryptodemo;

import com.bitoasis.cryptodemo.api.entity.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class CryptodemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptodemoApplication.class, args);
    }

}
