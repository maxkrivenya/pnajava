package com.example.apihell.repository;
import java.util.List;

import com.example.apihell.model.Skip;
import com.example.apihell.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkipRepository extends JpaRepository<Skip, String> {
    List<Skip> getSkipsByStudent(Student student);
    List<Skip> getSkipsByStudentId(String id);
    Skip getSkipById(String id);
    void delete(Skip skip);

    void deleteSkipById(String id);

    Skip save(Skip skip);
}