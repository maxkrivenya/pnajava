package com.example.apihell.service;

import com.example.apihell.model.Subject;

import java.util.List;

public interface SubjectService {
    public List<Subject> getAllByName(String name);

    public Subject getSubjectById(String id);

    public Subject save(Subject subject);

    public void deleteSubjectById(String id);

    public void logCache();
}
