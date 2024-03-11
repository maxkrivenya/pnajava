package com.example.apihell.service;

import com.example.apihell.model.Mark;
import com.example.apihell.model.Skip;
import com.example.apihell.model.Student;
import com.example.apihell.repository.MarksRepository;
import com.example.apihell.repository.SkipsRepository;
import com.example.apihell.repository.StudentRepository;
import com.example.apihell.repository.SemesterRepository;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final
    StudentRepository studentRepository;
    private final
    MarksRepository marksRepository;
    private final
    SemesterRepository semesterRepository;
    private final
    SkipsRepository skipsRepository;

    public StudentService(StudentRepository studentRepository, MarksRepository marksRepository, SemesterRepository semesterRepository, SkipsRepository skipsRepository) {
        this.studentRepository = studentRepository;
        this.marksRepository = marksRepository;
        this.semesterRepository = semesterRepository;
        this.skipsRepository = skipsRepository;
    }

    @Nullable
        public List<Student> getAllStudents(@RequestParam(required = false) String name) {
            List<Student> students = new ArrayList<>();
            try {
                if (name == null) {
                    studentRepository.findAll().forEach(students::add);
                } else {
                    studentRepository.findByNameContaining(name).forEach(students::add);
                }
            } catch (Exception e) {
                return students;
            }
            return students;
        }

        public Optional<Student> getStudentById(@PathVariable("id") String id) {
            Optional<Student> optionalStudent = studentRepository.findById(id);
            if(optionalStudent.isEmpty()){
                return optionalStudent;
            }
            Student student = optionalStudent.get();
            student.setMarks(marksRepository.getMarksByStudentId(id));
            return Optional.of(student);
        }

        public List<Integer> getAllForStudent(@PathVariable("id") String id){
            return marksRepository.getMarksByDateBetween(id);
        }

        public List<Integer> getAllForStudentForSubject(@PathVariable("id") String id, @PathVariable("subject") String subject){
            return marksRepository.getMarksForStudentForSubject(id, subject);
        }

        public List<String> getSubjectsByStudId(@PathVariable("id") String id){
            Optional<Student> student = studentRepository.findById(id);
            List<String> subjects = null;
            if(student.isEmpty()) {
                return subjects;
            }
            Student existingStudent = student.get();
            subjects = semesterRepository.getSubjectsBySpecAndSemNum(existingStudent.getSpec(), 2);
            return subjects;
        }

        @Nullable
        public Student createStudent(@RequestBody Student student) {
            try {
                return studentRepository
                        .save(new Student(student.getId(),student.getName(), student.getFaculty(), student.getSpec(), student.getGroup()));
            } catch (Exception e) {
                return student;
            }
        }

        public Student updateStudent(@PathVariable("id") String id, @RequestBody Student student) {
            Optional<Student> studentData = studentRepository.findById(id);

            if (studentData.isPresent()) {
                Student tempStudent = studentData.get();
                tempStudent.setName(student.getName());
                tempStudent.setFaculty(student.getFaculty());
                return student;
            } else {
                return new Student();
            }
        }

        public HttpStatus deleteStudent(@PathVariable("id") String id) {
            try {
                studentRepository.deleteById(id);
                return HttpStatus.NO_CONTENT;
            } catch (Exception e) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        public HttpStatus deleteAllStudents() {
            try {
                studentRepository.deleteAll();
                marksRepository.deleteAll();
                return HttpStatus.NO_CONTENT;
            } catch (Exception e) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        public List<Mark> getMarksByStudentId(String id){
            return marksRepository.getMarksByStudentId(id);
        }

        public List<Skip> getSkipsByStudentId(String id){
            return skipsRepository.getAllByStudentId(id);
        }
}
