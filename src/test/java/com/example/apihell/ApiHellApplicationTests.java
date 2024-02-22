package com.example.apihell;
import com.example.apihell.model.Student;
import com.example.apihell.repository.Repository;
import com.example.apihell.controller.Controller;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class ApiHellApplicationTests {

    @Test
    void contextLoads() {
        Controller controller = null;
        ResponseEntity<Student> student = controller.getStudentById("25050071");
    }

}
