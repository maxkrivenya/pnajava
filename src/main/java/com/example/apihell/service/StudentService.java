package com.example.apihell.service;

import com.example.apihell.model.Professor;
import com.example.apihell.model.Student;

import java.util.List;

public interface StudentService {
    public Student getStudentById(String id);
    public List<Student> getStudentsByGroupId(String id);
    public void deleteStudentById(String id);
    public Student save(Student student);
    public void logCache();

    //List<Professor> getProfessorsByStudentId(String id);
    List<String> getProfessorsByStudentId(String id);
}