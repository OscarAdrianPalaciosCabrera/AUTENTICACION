package com.crediya;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner run(PasswordEncoder encoder) {
        return args -> {
            String raw = "123456";
            String hash = encoder.encode(raw);
            System.out.println("BCrypt hash for '123456': " + hash);
        };
    }*/
}
