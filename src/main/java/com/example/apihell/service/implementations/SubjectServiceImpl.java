package com.example.apihell.service.implementations;

import com.example.apihell.model.Subject;
import com.example.apihell.repository.SubjectRepository;
import com.example.apihell.service.SubjectService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
    public List<Subject> getAllByName(String name){
        return subjectRepository.getAllByName(name);
    }
    public Subject getSubjectById(String id){
        return subjectRepository.getSubjectById(id);
    }
    public Subject save(Subject subject){
        return subjectRepository.save(subject);
    }
    public void deleteSubjectById(String id){
        subjectRepository.deleteSubjectById(id);
    }
}
