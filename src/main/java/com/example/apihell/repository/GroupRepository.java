package com.example.apihell.repository;

import com.example.apihell.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {

    List<Group> findAll();

    Group getGroupById(String id);

    Group getGroupByFacultyAndDegreeAndAndEducationTypeAndSemesterNumber(String faculty,
                                                                         String degree,
                                                                         String educationType,
                                                                         int semesterNumber
    );

    void deleteById(String id);

    Group save(Group group);
    }