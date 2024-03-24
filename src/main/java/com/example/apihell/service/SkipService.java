package com.example.apihell.service;

import com.example.apihell.model.Skip;
import com.example.apihell.model.Student;
import com.example.apihell.repository.SkipRepository;
import com.example.apihell.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkipService{
    private final StudentRepository studentRepository;
    private final SkipRepository skipRepository;
    public SkipService(SkipRepository skipRepository, StudentRepository studentRepository) {
        this.skipRepository = skipRepository;
        this.studentRepository = studentRepository;
    }

    public List<Skip> getSkipsByStudentId(String id){
        Student student = studentRepository.getStudentById(id);
        return skipRepository.getSkipsByStudent(student);
    }
}
