package com.example.apihell.repository;

import com.example.apihell.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, String> {
    Optional<Professor> findById(String id) ;

    List<Professor> findByNameContaining(String name);

    @Query(value = "select * from profs", nativeQuery=true)
    List<Professor> getAll();

    @Query(value="select name from profs", nativeQuery = true)
    List<String> getNames();
}