package com.example.apihell.controller;
import com.example.apihell.model.Mark;
import com.example.apihell.model.Professor;
import com.example.apihell.model.Skip;
import com.example.apihell.model.Student;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.example.apihell.service.StudentService;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@Transactional
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

    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") String id, @RequestBody Student student) {
        Student student1 = studentService.updateStudent(id, student);
        if(student1 == null){
            return new ResponseEntity<>(student, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(student1, HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") String id) {
        return new ResponseEntity<>(studentService.deleteStudent(id));
    }

    @DeleteMapping("/student")
    public ResponseEntity<HttpStatus> deleteAllStudents() {
        return new ResponseEntity<>(studentService.deleteAllStudents());
    }

    @Nullable
    @GetMapping("/student/{id}/markslist")
    public ResponseEntity<List<Mark>> getMarksByStudentId(@PathVariable("id") String id){
        List<Mark> list = studentService.getMarksByStudentId(id);
        if(list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/student/{id}/allskips")
    public ResponseEntity<List<Skip>> getAllSkipsByStudentId(@PathVariable("id") String id){
        List<Skip> list = studentService.getSkipsByStudentId(id);
        if(list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/student/{id}/professors")
    public ResponseEntity<List<Professor>> getProfessorsForStudent(@PathVariable("id") String id){
        List<Professor> list = studentService.getProfessorsByStudentId(id);
        if(list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }
    @GetMapping("/student/{id}/professorsnames")
    public ResponseEntity<List<String>> getProfessorNamesForStudent(@PathVariable("id") String id){
        List<String> list = studentService.getProfessorsNamesByStudentId(id);
        if(list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }
}


