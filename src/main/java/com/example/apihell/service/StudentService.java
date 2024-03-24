package com.example.apihell.service;

import com.example.apihell.model.Group;
import com.example.apihell.model.Student;
import com.example.apihell.repository.GroupRepository;
import com.example.apihell.repository.StudentRepository;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }
    @Nullable
    public Student getStudentById(String id){
        return studentRepository.getStudentById(id);
    }

    public List<Student> getStudentsByGroupId(String id){
        Group group = groupRepository.getGroupById(id);
        return studentRepository.getStudentsByGroup(group);
    }
}
