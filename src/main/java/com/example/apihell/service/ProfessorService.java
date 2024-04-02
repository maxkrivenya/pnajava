package com.example.apihell.service;

import com.example.apihell.model.Professor;

public interface ProfessorService {
    public Professor getProfessorById(String id);
    public Professor save(Professor professor);
    public void deleteProfessorById(String id);
}
