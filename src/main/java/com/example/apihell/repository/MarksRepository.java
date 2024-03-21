package com.example.apihell.repository;
import java.util.List;
import com.example.apihell.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MarksRepository extends JpaRepository<Mark, String> {

    @Query(value = "select mark from marks where student_id = ?1", nativeQuery = true)
    List<Integer> getMarksByDateBetween(String studId);

    @Query(value = "select mark from marks where student_id = ?1 and subject = ?2", nativeQuery = true)
    List<Integer>  getMarksForStudentForSubject(String studentId, String subject);

    List<Mark> getMarksByStudentId(String id);

    List<Mark> getMarksByProfessor(String professorName);

    @Query(value="SELECT * FROM marks", nativeQuery = true)
    List<Mark> getAll();
}
