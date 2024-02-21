package com.example.apihell.Controller;

import com.example.apihell.Repository.Repository;
import com.example.apihell.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    Repository repka;

    @GetMapping("/Students")
    public ResponseEntity<List<Student>> getAllStudents(@RequestParam(required = false) String Name) {
        try {
            List<Student> Students = new ArrayList<Student>();
            if (Name == null) {
                repka.findAll().forEach(Students::add);
            } else
                repka.findByNameContaining(Name).forEach(Students::add);

            if (Students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(Students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") String id) {
        Optional<Student> StudentData = repka.findById(id);

        if (StudentData.isPresent()) {
            return new ResponseEntity<>(StudentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Students")
    public ResponseEntity<Student> createStudent(@RequestBody Student Student) {
        try {
            Student _Student = repka
                    .save(new Student(Student.getId(),Student.getName(), Student.getFac(), Student.getSpec(), Student.getGroup()));
            return new ResponseEntity<>(_Student, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/Students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") String id, @RequestBody Student Student) {
        Optional<Student> StudentData = repka.findById(id);

        if (StudentData.isPresent()) {
            Student _Student = StudentData.get();
            _Student.setName(Student.getName());
            _Student.setFac(Student.getFac());
            return new ResponseEntity<>(repka.save(_Student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Students/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") String id) {
        try {
            repka.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/Students")
    public ResponseEntity<HttpStatus> deleteAllStudents() {
        try {
            repka.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}