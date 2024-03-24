package com.example.apihell.repository;

import com.example.apihell.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, String> {
    public Professor getProfessorById(String id);
}