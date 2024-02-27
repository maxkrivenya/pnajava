package com.example.demo;

import com.example.apihell.ApiHellApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
@SpringBootConfiguration
public class ApiHellApplicationTests {
    @Test
    void contextLoads() {
        SpringApplication.run(ApiHellApplication.class);
    }
}
