package com.example.apihell.repository;

import java.util.List;
import java.util.Optional;

import com.example.apihell.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findById(String id) ;
    List<Student> findByNameContaining(String name);
}

