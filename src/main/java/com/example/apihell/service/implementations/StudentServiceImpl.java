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
        return studentRepository.save(student);
    }

/*    public List<Professor> getProfessorsByStudentId(String id){
        String cacheKey = CacheComponent.MULTI_CACHE_KEY +
                CacheComponent.PROFESSOR_CACHE_KEY +
                "from" +
                CacheComponent.STUDENT_CACHE_KEY +
                id;
        List<Professor> professors = (List<Professor>) cache.get(cacheKey);
        if(professors==null){
            professors = studentRepository.getProfessorsByStudentId(id);
            cache.put(cacheKey,professors);
        }
        return professors;
    }

 */
public List<String> getProfessorsByStudentId(String id){
    String cacheKey = CacheComponent.MULTI_CACHE_KEY +
            CacheComponent.PROFESSOR_CACHE_KEY +
            "from" +
            CacheComponent.STUDENT_CACHE_KEY +
            id;
    List<String> professors = (List<String>) cache.get(cacheKey);
    if(professors==null){
        professors = studentRepository.getProfessorsByStudentId(id);
        cache.put(cacheKey,professors);
    }
    return professors;
}

    public void logCache(){
        cache.log();
    }
}
