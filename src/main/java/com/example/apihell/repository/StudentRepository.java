package com.example.apihell.repository;

import com.example.apihell.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    Student getStudentById(String id);

    List<Student> getStudentsByGroupId(String groupId);

    void deleteStudentById(String id);

    @Override
    Student save(Student student);

    @Query(value="select distinct s.surname from student s inner join professors p on p.surname like ?1 and s.surname like ?1",
        nativeQuery = true
    )
    List<String> getSameSurnameLike(String parameter);
}
