package com.example.apihell.repository;

import java.util.List;
import java.util.Optional;

import com.example.apihell.model.Mark;
import com.example.apihell.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

    public interface MarksRep extends JpaRepository<Mark, String> {

    @Query(value = "select mark from marks where id = ?1", nativeQuery = true)
    List<Integer> getMarksByDateBetween(String studId);
}
