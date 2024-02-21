package com.example.apihell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

@SpringBootApplication
public class ApiHellApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiHellApplication.class, args);
        System.out.println("hello world");
    }
}
