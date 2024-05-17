package com.example.apihell.repository;

import com.example.apihell.model.Mark;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, String> {
    @Nullable
    List<Mark> getMarksByStudentId(String id);

    Mark getMarkById(String id);

    void deleteMarkById(String id);

    Mark save(Mark mark);
}
