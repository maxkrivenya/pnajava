package com.example.apihell.service.implementations;

import com.example.apihell.model.Group;
import com.example.apihell.model.Student;
import com.example.apihell.repository.GroupRepository;
import com.example.apihell.repository.StudentRepository;
import com.example.apihell.service.StudentService;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    public StudentServiceImpl(StudentRepository studentRepository, GroupRepository groupRepository) {
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
    public void deleteStudentById(String id){
        studentRepository.deleteStudentById(id);
    }
    public Student save(Student student){
        return studentRepository.save(student);
    }
}