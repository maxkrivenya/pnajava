package com.example.apihell.repository;
import java.util.List;

import com.example.apihell.model.Skip;
import com.example.apihell.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkipRepository extends JpaRepository<Skip, String> {
    List<Skip> getSkipsByStudent(Student student);

    Skip getSkipById(String id);

    void deleteSkipById(String id);
    Skip save(Skip skip);
}