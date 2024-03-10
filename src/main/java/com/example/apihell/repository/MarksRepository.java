package com.example.apihell.repository;
import java.util.List;
import com.example.apihell.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;

public interface MarksRepository extends JpaRepository<Mark, String> {

    @Query(value = "select mark from marks where id = ?1", nativeQuery = true)
    List<Integer> getMarksByDateBetween(String studId);

    @Query(value = "select mark from marks where id = ?1 and subj = ?2", nativeQuery = true)
    List<Integer>  getMarksForStudentForSubject(String studentId, String subject);

    List<Mark> getMarksByStudentId(String id);

    void deleteMarksByStudentId(String studentId);
}
