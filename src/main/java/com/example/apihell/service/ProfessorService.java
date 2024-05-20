package com.example.apihell.service;

import com.example.apihell.model.Professor;

import java.util.List;

public interface ProfessorService {

    List<Professor> getAllProfessors();

    Professor getProfessorById(String id);

    Professor save(Professor professor);

    void deleteProfessorById(String id);

}
