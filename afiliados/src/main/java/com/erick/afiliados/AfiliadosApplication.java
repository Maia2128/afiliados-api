package com.erick.afiliados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AfiliadosApplication {

    public static void main(String[] args) {
        SpringApplication.run(AfiliadosApplication.class, args);
    }
}