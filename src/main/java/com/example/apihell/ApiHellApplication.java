package com.example.apihell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories("com.example.apihell")
public class ApiHellApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiHellApplication.class, args);
    }
}