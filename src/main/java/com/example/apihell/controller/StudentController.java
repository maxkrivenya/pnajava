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
            return new ResponseEntity(studentService.getAllStudents(name), HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") String id) {
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @GetMapping("/student/{id}/marks")
    public ResponseEntity<List<Integer>> getAllForStudent(@PathVariable("id") String id){
            return new ResponseEntity<>(studentService.getAllForStudent(id), HttpStatus.OK);
    }
    @GetMapping("/student/{id}/marks/{subject}")
    public ResponseEntity<List<Integer>> getAllForStudentForSubject(@PathVariable("id") String id, @PathVariable("subject") String subject){
            return new ResponseEntity<>(studentService.getAllForStudentForSubject(id, subject), HttpStatus.OK);
    }

    @GetMapping("/student/{id}/subjects")
    public ResponseEntity<List<String>> getSubjectsByStudId(@PathVariable("id") String id){
        return new ResponseEntity<>(studentService.getSubjectsByStudId(id), HttpStatus.OK);
    }

    @PostMapping("/student")
    @Nullable
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.OK);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") String id, @RequestBody Student student) {
        return new ResponseEntity<>(studentService.updateStudent(id, student), HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") String id) {
        return new ResponseEntity<>(studentService.deleteStudent(id), HttpStatus.OK);
    }

    @DeleteMapping("/student")
    public ResponseEntity<HttpStatus> deleteAllstudents() {
        return new ResponseEntity<>(studentService.deleteAllstudents(), HttpStatus.OK);
    }
}