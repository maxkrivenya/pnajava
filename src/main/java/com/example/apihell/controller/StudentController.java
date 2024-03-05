package com.example.apihell.controller;
import com.example.apihell.model.Student;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.apihell.service.StudentService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


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
        List<Student> students = studentService.getAllStudents(name);
        if(students.isEmpty()) {
            return new ResponseEntity<>(students, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(students, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable("id") String id) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(student, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student/{id}/marks")
    public ResponseEntity<List<Integer>> getAllForStudent(@PathVariable("id") String id){
        List<Integer> list = studentService.getAllForStudent(id);
        if(list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/student/{id}/marks/{subject}")
    public ResponseEntity<List<Integer>> getAllForStudentForSubject(@PathVariable("id") String id, @PathVariable("subject") String subject){
        List<Integer> list = studentService.getAllForStudentForSubject(id, subject);
        if(list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/student/{id}/subjects")
    public ResponseEntity<List<String>> getSubjectsByStudId(@PathVariable("id") String id){
        List<String>  list = studentService.getSubjectsByStudId(id);
        if(list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/student")
    @Nullable
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student student1 = studentService.createStudent(student);
        if(Objects.equals(student1, new Student())){
            return new ResponseEntity<>(student, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @Nullable
    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") String id, @RequestBody Student student) {
        Student student1 = studentService.updateStudent(id, student);
        if(student1 == null){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(student1, HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") String id) {
        return new ResponseEntity<>(studentService.deleteStudent(id));
    }

    @DeleteMapping("/student")
    public ResponseEntity<HttpStatus> deleteAllStudents() {
        return new ResponseEntity<>(studentService.deleteAllstudents());
    }

}
