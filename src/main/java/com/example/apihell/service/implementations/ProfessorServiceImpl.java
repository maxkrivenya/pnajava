package com.example.apihell.service.implementations;

import com.example.apihell.model.Professor;
import com.example.apihell.repository.ProfessorRepository;
import com.example.apihell.service.ProfessorService;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    private final ProfessorRepository professorRepository;
    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Nullable
    public Professor getProfessorById(String id){
        return professorRepository.getProfessorById(id);
    }
    public Professor save(Professor professor){
        return professorRepository.save(professor);
    }
    public void deleteProfessorById(String id){
        professorRepository.deleteProfessorById(id);
    }
}
