package com.example.apihell.repository;

import com.example.apihell.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {
    Group getGroupById(String id);

    Group getGroupByFacultyAndDegreeAndAndEducationTypeAndSemesterNumber(String faculty,
                                                                         String degree,
                                                                         String educationType,
                                                                         int semesterNumber
    );

    void deleteById(String id);

    Group save(Group group);
    }