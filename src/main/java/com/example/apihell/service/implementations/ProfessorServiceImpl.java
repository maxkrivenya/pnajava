package com.example.apihell.service.implementations;

import com.example.apihell.components.CacheComponent;
import com.example.apihell.model.Professor;
import com.example.apihell.repository.ProfessorRepository;
import com.example.apihell.service.ProfessorService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    @Autowired
    private final ProfessorRepository professorRepository;
    private final CacheComponent cache;

    @Autowired
    public ProfessorServiceImpl(CacheComponent cache, ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
        this.cache = cache;
    }

    public List<Professor> getAllProfessors(){
        String cacheKey = CacheComponent.PROFESSOR_CACHE_KEY + CacheComponent.GET_ALL;
        List<Professor> professors = (List<Professor>) cache.get(cacheKey);
        if(professors == null){
            professors = professorRepository.findAll();
            cache.put(cacheKey, professors);
        }
        return professors;
    }

    @Nullable
    public Professor getProfessorById(String id){
        String cacheKey = CacheComponent.PROFESSOR_CACHE_KEY + id;
        Professor professor = (Professor) cache.get(cacheKey);
        if(professor==null){
            professor = professorRepository.getProfessorById(id);
            cache.put(cacheKey,professor);
        }
        return professor;
    }

    public Professor save(Professor professor){
        String cacheKey = CacheComponent.PROFESSOR_CACHE_KEY + professor.getId();
        cache.remove(cacheKey);
        cache.put(cacheKey,professor);
        return professorRepository.save(professor);
    }

    public void deleteProfessorById(String id){
        cache.remove(CacheComponent.PROFESSOR_CACHE_KEY + id);
        professorRepository.deleteProfessorById(id);
    }

    public void logCache(){
        cache.log();
    }
}