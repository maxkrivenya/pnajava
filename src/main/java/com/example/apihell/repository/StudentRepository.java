package com.example.apihell.repository;

import com.example.apihell.model.Student;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Nullable
    Student getStudentById(String id);
}

