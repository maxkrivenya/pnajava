package com.example.demo;

import com.example.apihell.ApiHellApplication;
import jakarta.xml.ws.http.HTTPException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
@SpringBootConfiguration
class ApiHellApplicationTests {
    @Test
    void contextLoads() throws HTTPException {
        SpringApplication.run(ApiHellApplication.class);
    }
}
