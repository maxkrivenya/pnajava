package com.example.apihell.service.implementations;

import com.example.apihell.components.CacheComponent;
import com.example.apihell.model.Group;
import com.example.apihell.model.Professor;
import com.example.apihell.model.Student;
import com.example.apihell.repository.GroupRepository;
import com.example.apihell.repository.StudentRepository;
import com.example.apihell.service.StudentService;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final CacheComponent cache;

    public StudentServiceImpl(CacheComponent cache, StudentRepository studentRepository, GroupRepository groupRepository) {
        this.cache = cache;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Nullable
    public Student getStudentById(String id){
        String key = "student" + id;
        Student student = (Student) cache.get(key);
        if (student != null) {
            return student;
        }else{
            student = studentRepository.getStudentById(id);
            cache.put(CacheComponent.STUDENT_CACHE_KEY + id, student);
            return student;
        }
    }

    public List<Student> getStudentsByGroupId(String id){
        String groupKey = "group" + id;
        Group group = (Group) cache.get(groupKey);
        if(group == null){
            group = groupRepository.getGroupById(id);
            cache.put(groupKey, group);
        }
        String studentsKey = CacheComponent.MULTI_CACHE_KEY + CacheComponent.STUDENT_CACHE_KEY + id;
        List<Student> students = (List<Student>) cache.get(studentsKey);
        if(students == null){
            students = studentRepository.getStudentsByGroup(group);
            cache.put(studentsKey, students);
        }
        return students;
    }

    public void deleteStudentById(String id){
        studentRepository.deleteStudentById(id);
        cache.remove(CacheComponent.STUDENT_CACHE_KEY + id);
    }

    public Student save(Student student){
        String cacheKey = CacheComponent.STUDENT_CACHE_KEY + student.getId();
        Student existing = (Student)cache.get(cacheKey);
        cache.remove(cacheKey);
        if(existing==null){
            existing = studentRepository.getStudentById(student.getId());
        }
        if(existing != null){
            existing.fill(student);
            return studentRepository.save(existing);
        }
        return studentRepository.save(student);
    }

    public List<String> getSameSurname(){
        String cacheKey = "sameSurname";
        List<String> professors;
        professors = (List<String>) cache.get(cacheKey);

        if(professors==null){
            professors = studentRepository.getSameSurname();
            cache.put(cacheKey,professors);
        }
        return professors;
    }

    public boolean studentExists(String id){
        String cacheKey = CacheComponent.STUDENT_CACHE_KEY + id;
        Student student = (Student)cache.get(cacheKey);
        if(student == null){
            student = studentRepository.getStudentById(id);
            if(student == null){
                return false;
            }
        }
        return true;
    }

    public void logCache(){
        cache.log();
    }
}
