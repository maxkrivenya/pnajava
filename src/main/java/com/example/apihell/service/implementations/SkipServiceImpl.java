package com.example.apihell.service.implementations;

import com.example.apihell.components.CacheComponent;
import com.example.apihell.model.Skip;
import com.example.apihell.repository.SkipRepository;
import com.example.apihell.service.SkipService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkipServiceImpl implements SkipService {
    private final SkipRepository skipRepository;
    private final CacheComponent cache;

    public SkipServiceImpl(CacheComponent cache, SkipRepository skipRepository) {
        this.skipRepository = skipRepository;
        this.cache = cache;
    }

    public List<Skip> getSkipsByStudentId(String id){
        List<Skip> skips;
        String cacheKey = CacheComponent.MULTI_CACHE_KEY + CacheComponent.SKIP_CACHE_KEY + id;
        skips = (List<Skip>) cache.get(cacheKey);
        if(skips == null){
            skips =  skipRepository.getSkipsByStudentId(id);
            cache.put(cacheKey, skips);
        }
        return skips;
    }

    public Skip save(Skip skip){
        cache.remove(CacheComponent.SKIP_CACHE_KEY);
        cache.put(CacheComponent.SKIP_CACHE_KEY + skip.getId(), skip);
        return skipRepository.save(skip);
    }

    public Skip getSkipById(String id){
        String cacheKey = CacheComponent.SKIP_CACHE_KEY + id;
        Skip skip = (Skip)cache.get(cacheKey);
        if(skip==null){
            skip = skipRepository.getSkipById(id);
            cache.put(cacheKey,skip);
        }
        return skip;
    }

    public void deleteSkip(Skip skip){
        cache.remove(CacheComponent.SKIP_CACHE_KEY + skip.getId());
        skipRepository.delete(skip);
    }

    public void deleteSkipById(String id){
        cache.remove(CacheComponent.SKIP_CACHE_KEY + id);
        skipRepository.deleteSkipById(id);
    }

    public void logCache(){
        cache.log();
    }
}
