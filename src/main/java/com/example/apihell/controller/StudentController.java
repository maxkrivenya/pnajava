package com.example.apihell.controller;

import com.example.apihell.exception.ResourceNotFoundException;
import com.example.apihell.model.Mark;
import com.example.apihell.model.Skip;
import com.example.apihell.model.Student;
import com.example.apihell.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping("/{id}/skips")
    public ResponseEntity<List<Skip>> getStudentsSkipsById(@PathVariable("id") String id) {
        Student student = studentService.getStudentById(id);
        if(student==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (student.getMarks().isEmpty()) {
            return new ResponseEntity<>(student.getSkips(), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(student.getSkips(), HttpStatus.OK);
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

    @PostMapping(value="/new/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student savedStudent  = studentService.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PutMapping(value="/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student student){
        Student updatedStudent  = studentService.getStudentById(id);
        if(updatedStudent == null){
            throw new ResourceNotFoundException("no such student!");
        }
        updatedStudent.setId(student.getId());
        updatedStudent.setName(student.getName());
        updatedStudent.setSurname(student.getSurname());
        updatedStudent.setSurname(student.getPatronim());

        studentService.save(updatedStudent);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable(name="id") String id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok("deleted student " + id);
    }

    @GetMapping(path="/cache")
    public HttpStatus logCache(){
        studentService.logCache();
        return HttpStatus.NO_CONTENT;
    }
}
