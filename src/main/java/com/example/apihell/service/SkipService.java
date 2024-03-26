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

    public Skip save(Skip skip){
        return skipRepository.save(skip);
    }

    public Skip getSkipById(String id){
        return skipRepository.getSkipById(id);
    }

    public void deleteSkip(Skip skip){
        skipRepository.delete(skip);
    }

    public void deleteSkipById(String id){
        skipRepository.deleteSkipById(id);
    }
}
