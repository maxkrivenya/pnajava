package com.example.apihell.repository;

import com.example.apihell.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, String> {
    Professor getProfessorById(String id);

    Professor save(Professor professor);

    void deleteProfessorById(String id);

}