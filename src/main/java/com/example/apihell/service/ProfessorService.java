package com.example.apihell.service;

import com.example.apihell.model.Professor;

public interface ProfessorService {
    Professor getProfessorById(String id);

    Professor save(Professor professor);

    void deleteProfessorById(String id);

}
