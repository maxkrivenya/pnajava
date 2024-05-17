package com.example.apihell.repository;

import com.example.apihell.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    List<Subject> getAllByName(String name);

    Subject getSubjectById(String id);

    void deleteSubjectById(String id);

    Subject save(Subject subject);
}
