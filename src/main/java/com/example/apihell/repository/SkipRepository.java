package com.example.apihell.repository;
import java.util.List;

import com.example.apihell.model.Professor;
import com.example.apihell.model.Skip;
import com.example.apihell.model.Student;
import com.example.apihell.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkipRepository extends JpaRepository<Skip, String> {
    List<Skip> getSkipsByStudent(Student student);

//    List<Skip> getSkipsBySubjectAndDateBetween(Subject subject, String date1, String date2);
}