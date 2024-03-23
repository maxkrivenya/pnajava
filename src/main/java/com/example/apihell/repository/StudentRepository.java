package com.example.apihell.repository;

import java.util.List;
import java.util.Optional;

import com.example.apihell.model.Student;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Nullable
    Student getStudentById(String id);
}

