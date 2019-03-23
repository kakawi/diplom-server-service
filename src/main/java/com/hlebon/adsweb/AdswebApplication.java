package com.hlebon.adsweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AdswebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdswebApplication.class, args);
    }

}
