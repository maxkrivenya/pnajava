package com.example.apihell.repository;
import java.util.List;

import com.example.apihell.model.Skip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkipsRepository extends JpaRepository<Skip, String> {
    List<Skip> getSkipsByStudentId(String id);

    List<Skip> getSkipsByProfessorId(String id);

    List<Skip> getSkipsBySubjectId(String id);

    List<Skip> getSkipsBySubjectIdAndDateBetween(String id, String date1, String date2);
}