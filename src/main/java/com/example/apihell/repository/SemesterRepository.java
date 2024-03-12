package com.example.apihell.repository;
import java.util.List;

import com.example.apihell.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SemesterRepository extends JpaRepository<Semester, String> {

    @Query(value = "select subj from sem where spec = ?1 and semnum = ?2", nativeQuery = true)
    List<String> getSubjectsBySpecAndSemNum(String spec, int semesterNumber);

    @Query(value = "select lecturer from sem where student_id = ?1", nativeQuery = true)
    List<String> getLecturersByStudentId(String id);
}
