package com.example.apihell.controller;
import com.example.apihell.model.Student;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.apihell.service.StudentService;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    @Nullable
    public ResponseEntity<List<Student>> getAllStudents(@RequestParam(required = false) String name) {
            return studentService.getAllStudents(name);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") String id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/student/{id}/marks")
    public ResponseEntity<List<Integer>> getAllForStudent(@PathVariable("id") String id){
            return studentService.getAllForStudent(id);
    }
    @GetMapping("/student/{id}/marks/{subject}")
    public ResponseEntity<List<Integer>> getAllForStudentForSubject(@PathVariable("id") String id, @PathVariable("subject") String subject){
            return studentService.getAllForStudentForSubject(id, subject);
    }

    @GetMapping("/student/{id}/subjects")
    public ResponseEntity<List<String>> getSubjectsByStudId(@PathVariable("id") String id){
        return studentService.getSubjectsByStudId(id);
    }



    @PostMapping("/student")
    @Nullable
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") String id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") String id) {
        return studentService.deleteStudent(id);
    }


    @DeleteMapping("/student")
    public ResponseEntity<HttpStatus> deleteAllstudents() {
        return studentService.deleteAllstudents();
    }
}