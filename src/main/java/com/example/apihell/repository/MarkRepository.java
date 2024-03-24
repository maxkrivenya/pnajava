package com.example.apihell.repository;

import com.example.apihell.model.Mark;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkRepository extends JpaRepository<Mark, String> {
    @Nullable
    List<Mark> getMarksByStudentId(String id);
}
