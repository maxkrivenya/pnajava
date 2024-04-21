package com.example.apihell.service;

import com.example.apihell.model.Student;

import java.util.List;
import java.util.OptionalDouble;

public interface StudentService {
    Student getStudentById(String id);

    List<Student> getStudentsByGroupId(String id);

    void deleteStudentById(String id);

    Student save(Student student);

    boolean studentExists(String id);

    List<String> getSameSurnameLike(String surnameLike);

    OptionalDouble getAverageScoreInGroup(String groupId);
}