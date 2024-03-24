package com.example.apihell.service;

import com.example.apihell.model.Mark;
import com.example.apihell.repository.MarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkService{
    private final MarkRepository markRepository;
    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public List<Mark> getMarksByStudentId(String id){
        return markRepository.getMarksByStudentId(id);
    }
}
