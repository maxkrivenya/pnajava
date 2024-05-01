package com.example.apihell.service;

import com.example.apihell.model.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> getAllByName(String name);

    Subject getSubjectById(String id);

    Subject save(Subject subject);

    void deleteSubjectById(String id);

}
