package com.example.apihell.service;

import com.example.apihell.model.Professor;
import com.example.apihell.repository.ProfessorRepository;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Nullable
    public Professor getProfessorById(String id){
        return professorRepository.getProfessorById(id);
    }
}
