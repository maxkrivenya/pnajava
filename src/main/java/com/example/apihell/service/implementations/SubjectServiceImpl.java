package com.example.apihell.service.implementations;

import com.example.apihell.components.CacheComponent;
import com.example.apihell.model.Subject;
import com.example.apihell.repository.SubjectRepository;
import com.example.apihell.service.SubjectService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final CacheComponent cache;
    public SubjectServiceImpl(CacheComponent cache, SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
        this.cache = cache;
    }

    public List<Subject> getAllByName(String name){
        String cacheKey = CacheComponent.MULTI_CACHE_KEY + CacheComponent.SUBJECT_CACHE_KEY + name;
        List<Subject> subjects = (List<Subject>) cache.get(cacheKey);
        if(subjects==null){
            subjects = subjectRepository.getAllByName(name);
            cache.put(cacheKey,subjects);
        }
        return subjects;
    }
    public Subject getSubjectById(String id){
        String cacheKey = CacheComponent.SUBJECT_CACHE_KEY + id;
        Subject subject = (Subject) cache.get(cacheKey);
        if(subject==null){
            subject = subjectRepository.getSubjectById(id);
            cache.put(cacheKey,subject);
        }
        return subject;
    }
    public Subject save(Subject subject){
        String cacheKey = CacheComponent.SUBJECT_CACHE_KEY + subject.getId();
        cache.remove(cacheKey);
        cache.put(cacheKey, subject);
        return subjectRepository.save(subject);
    }
    public void deleteSubjectById(String id){
        cache.remove(CacheComponent.SUBJECT_CACHE_KEY + id);
        subjectRepository.deleteSubjectById(id);

    }

    public void logCache(){
        cache.log();
    }
}
