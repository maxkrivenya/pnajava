package com.example.apihell.service;

import com.example.apihell.model.Mark;
import com.example.apihell.repository.MarksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarksService {
    private final
    MarksRepository marksRepository;

    public MarksService(MarksRepository marksRepository) {
        this.marksRepository = marksRepository;
    }

    public List<Mark> getMarksByStudId(String id){
        return marksRepository.getMarksByStudentId(id);
    }

    public List<Mark> getMarksByProfessor(String name){return marksRepository.getMarksByProfessor(name);}
    public List<Mark> getAll(){
        return marksRepository.getAll();
    }
}

