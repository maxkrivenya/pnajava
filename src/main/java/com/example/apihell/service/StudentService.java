package com.example.apihell.service;

import com.example.apihell.model.Student;
import com.example.apihell.repository.MarksRep;
import com.example.apihell.repository.Repository;
import com.example.apihell.repository.SemRep;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final
    Repository repka;
    private final
    MarksRep marksRep;
    private final
    SemRep semRep;

    public StudentService(Repository repka, MarksRep marksRep, SemRep semRep) {
        this.repka = repka;
        this.marksRep = marksRep;
        this.semRep = semRep;
    }


    @Nullable
        public ResponseEntity<List<Student>> getAllStudents(@RequestParam(required = false) String name) {
            List<Student> students = new ArrayList<>();
            try {
                if (name == null) {
                    repka.findAll().forEach(students::add);
                } else
                    repka.findByNameContaining(name).forEach(students::add);

                if (students.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(students, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(students, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        public ResponseEntity<Student> getStudentById(@PathVariable("id") String id) {
            Optional<Student> studentData = repka.findById(id);

            if (studentData.isPresent()) {
                return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        public ResponseEntity<List<Integer>> getAllForStudent(@PathVariable("id") String id){
            List<Integer> marks = marksRep.getMarksByDateBetween(id);
            if (marks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(marks, HttpStatus.OK);
        }

        public ResponseEntity<List<Integer>> getAllForStudentForSubject(@PathVariable("id") String id, @PathVariable("subject") String subject){
            List<Integer> marks;
            marks = marksRep.getMarksForStudentForSubject(id, subject);
            if (marks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(marks, HttpStatus.OK);
        }

        public ResponseEntity<List<String>> getSubjectsByStudId(@PathVariable("id") String id){
            Optional<Student> student = repka.findById(id);
            List<String> subjects = null;

            if(!student.isPresent()) {
                return new ResponseEntity<>(subjects, HttpStatus.NO_CONTENT);
            }

            Student existingStudent = student.get();
            subjects = semRep.getSubjectsBySpecAndSemNum(existingStudent.getSpec(), 2);
            if(subjects.isEmpty()){
                return new ResponseEntity<>(subjects,HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(subjects,HttpStatus.OK);
            }
        }

        @Nullable
        public ResponseEntity<Student> createStudent(@RequestBody Student student) {
            try {
                Student tempStudent = repka
                        .save(new Student(student.getId(),student.getName(), student.getFac(), student.getSpec(), student.getGroup()));
                return new ResponseEntity<>(tempStudent, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(student, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        public ResponseEntity<Student> updateStudent(@PathVariable("id") String id, @RequestBody Student student) {
            Optional<Student> studentData = repka.findById(id);

            if (studentData.isPresent()) {
                Student tempStudent = studentData.get();
                tempStudent.setName(student.getName());
                tempStudent.setFac(student.getFac());
                return new ResponseEntity<>(repka.save(tempStudent), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") String id) {
            try {
                repka.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        public ResponseEntity<HttpStatus> deleteAllstudents() {
            try {
                repka.deleteAll();
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
}
