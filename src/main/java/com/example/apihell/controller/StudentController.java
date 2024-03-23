package com.example.apihell.controller;

import com.example.apihell.model.PersonalData;
import com.example.apihell.model.Student;
import com.example.apihell.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@Transactional
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") String id) {
        Student student = studentService.getStudentById(id);
        if (student==null) {
            return new ResponseEntity<>(student, HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
    }

    @GetMapping("/student/{id}/personaldata")
    public ResponseEntity<PersonalData> getStudentPersonalDataById(@PathVariable("id") String id) {
        Student student = studentService.getStudentById(id);
        if (student==null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(student.getPersonalData(), HttpStatus.OK);
        }
    }
}
