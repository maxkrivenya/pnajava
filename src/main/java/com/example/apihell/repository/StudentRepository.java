package com.example.apihell.repository;

import com.example.apihell.model.Group;
import com.example.apihell.model.Student;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Nullable
    Student getStudentById(String id);
    List<Student> getStudentsByGroup(Group group);
    void deleteStudentById(String id);
    @Override
    Student save(Student student);
}
