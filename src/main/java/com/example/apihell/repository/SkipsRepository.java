package com.example.apihell.repository;
import java.util.List;
import com.example.apihell.model.Skip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkipsRepository extends JpaRepository<Skip, String> {
    public List<Skip> getAllByStudentId(String studentId);
    public List<Skip> getAllByStudentIdAndSemesterNumber(String studentId, int semesterNumber);
    public List<Skip> getAllByStudentIdAndSemesterNumberAndSubject(String studentId, int semesterNumber, String subject);
    public void deleteAllByStudentId(String studentId);

}