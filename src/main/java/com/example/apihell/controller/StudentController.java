package com.example.apihell.controller;

import com.example.apihell.model.Mark;
import com.example.apihell.model.Student;
import com.example.apihell.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/student")
@Transactional
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") String id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return new ResponseEntity<>(student, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}/marks")
    public ResponseEntity<List<Mark>> getStudentsMarksById(@PathVariable("id") String id) {
        Student student = studentService.getStudentById(id);
        if(student==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (student.getMarks().isEmpty()) {
            return new ResponseEntity<>(student.getMarks(), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(student.getMarks(), HttpStatus.OK);
        }
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<List<Student>> getStudentsByGroupId(@PathVariable("id") String id) {
        List<Student> students = studentService.getStudentsByGroupId(id);
        if (students.isEmpty()) {
            return new ResponseEntity<>(students, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable(name="id") String id) throws Exception{
        studentService.deleteStudentById(id);
        return ResponseEntity.ok("deleted student " + id);
    }
}
