package com.example.apihell.service;

import com.example.apihell.model.Student;
import com.example.apihell.repository.StudentRepository;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Nullable
    public Student getStudentById(String id){
        return studentRepository.getStudentById(id);
    }
}
