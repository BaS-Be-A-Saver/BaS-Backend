package com.GDGoC.BaS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BaSApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaSApplication.class, args);
    }

}
