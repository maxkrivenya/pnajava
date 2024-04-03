package com.example.apihell.service.implementations;

import com.example.apihell.components.CacheComponent;
import com.example.apihell.model.Professor;
import com.example.apihell.repository.ProfessorRepository;
import com.example.apihell.service.ProfessorService;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    private final ProfessorRepository professorRepository;
    private final CacheComponent cache;

    public ProfessorServiceImpl(CacheComponent cache, ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
        this.cache = cache;
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