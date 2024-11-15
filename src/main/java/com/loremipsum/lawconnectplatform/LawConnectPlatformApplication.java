package com.loremipsum.lawconnectplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LawConnectPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(LawConnectPlatformApplication.class, args);
    }

}
