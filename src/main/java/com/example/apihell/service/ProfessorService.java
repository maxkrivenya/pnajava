package com.example.apihell.service;

import com.example.apihell.model.Professor;
import com.example.apihell.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfessorService {
    private final
    ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<Professor> getAllProfessors() {
        return professorRepository.getAll();
    }


    public List<String> getNames(){
        return professorRepository.getNames();
    }
}
