package com.example.apihell.service.implementations;

import com.example.apihell.components.CacheComponent;
import com.example.apihell.model.Mark;
import com.example.apihell.model.Skip;
import com.example.apihell.model.Student;
import com.example.apihell.repository.SkipRepository;
import com.example.apihell.repository.StudentRepository;
import com.example.apihell.service.SkipService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkipServiceImpl implements SkipService {
    private final StudentRepository studentRepository;
    private final SkipRepository skipRepository;
    private final CacheComponent cache;

    public SkipServiceImpl(CacheComponent cache, StudentRepository studentRepository, SkipRepository skipRepository) {
        this.studentRepository = studentRepository;
        this.skipRepository = skipRepository;
        this.cache = cache;
    }

    public List<Skip> getSkipsByStudentId(String id){
        List<Skip> skips;
        String cacheKey = cache.multiCacheKey + cache.skipCacheKey + id;
        skips = (List<Skip>) cache.get(cacheKey);
        if(skips == null){
            skips =  skipRepository.getSkipsByStudentId(id);
            cache.put(cacheKey, skips);
        }
        return skips;
    }
    public Skip save(Skip skip){
        cache.remove(cache.skipCacheKey);
        cache.put(cache.skipCacheKey + skip.getId(), skip);
        return skipRepository.save(skip);
    }
    public Skip getSkipById(String id){
        String cacheKey = cache.skipCacheKey + id;
        Skip skip = (Skip)cache.get(cacheKey);
        if(skip==null){
            skip = skipRepository.getSkipById(id);
            cache.put(cacheKey,skip);
        }
        return skip;
    }
    public void deleteSkip(Skip skip){
        cache.remove(cache.skipCacheKey + skip.getId());
        skipRepository.delete(skip);
    }
    public void deleteSkipById(String id){
        cache.remove(cache.skipCacheKey + id);
        skipRepository.deleteSkipById(id);
    }
}
