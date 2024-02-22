package com.example.apihell.controller;

import com.example.apihell.Repository.Repository;
import com.example.apihell.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
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
    final
    Repository repka;

    public Controller(Repository repka) {
        this.repka = repka;
    }

    @GetMapping("/student")
    @Nullable
    public ResponseEntity<List<Student>> getAllStudents(@RequestParam(required = false) String name) {
        try {
            List<Student> studentList = new ArrayList<>();
            if (name == null) {
                repka.findAll().forEach(studentList::add);
            } else
                repka.findByNameContaining(name).forEach(studentList::add);

            if (studentList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(studentList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") String id) {
        Optional<Student> studentEntry = repka.findById(id);

        return studentEntry.map(student -> new ResponseEntity<>(student, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/Students")
    public ResponseEntity<Student> createStudent(@RequestBody Student inputStudent) {
        try {
            Student newStudent = repka
                    .save(
                new Student(inputStudent.getId(),inputStudent.getName(), inputStudent.getFac(), inputStudent.getSpec(), inputStudent.getGroup()
            ));
            return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/Students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") String id, @RequestBody Student inputStudent) {
        Optional<Student> studentEntry  = repka.findById(id);

        if (studentEntry.isPresent()) {
            Student newStudent = new Student();
            inputStudent.setId      (inputStudent.getId()   );
            inputStudent.setName    (inputStudent.getName() );
            inputStudent.setFac     (inputStudent.getFac()  );
            inputStudent.setSpec    (inputStudent.getSpec() );
            inputStudent.setGroup   (inputStudent.getGroup());
            return new ResponseEntity<>(repka.save(newStudent), HttpStatus.OK);
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