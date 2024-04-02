package com.example.apihell.service.implementations;

import com.example.apihell.components.CacheComponent;
import com.example.apihell.model.Mark;
import com.example.apihell.repository.MarkRepository;
import com.example.apihell.service.MarkService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MarkServiceImpl implements MarkService {
    private final MarkRepository markRepository;
    private final CacheComponent cache;
    public MarkServiceImpl(CacheComponent cache, MarkRepository markRepository) {
        this.markRepository = markRepository;
        this.cache = cache;
    }

    public List<Mark> getMarksByStudentId(String id){
        List<Mark> marks;
        String cacheKey = cache.multiCacheKey + cache.markCacheKey + id;
        marks = (List<Mark>) cache.get(cacheKey);
        if(marks == null){
            marks =  markRepository.getMarksByStudentId(id);
            cache.put(cacheKey, marks);
        }
        return marks;
    }
    public Mark save(Mark mark){
        cache.remove(cache.markCacheKey + mark.getId());
        cache.put(cache.markCacheKey + mark.getId(),mark);
        return markRepository.save(mark);
    }
    public void deleteMarkById(String id){
        cache.remove(cache.markCacheKey + id);
        markRepository.deleteMarkById(id);
    }
    public Mark getMarkById(String id) {
        Mark mark = (Mark) cache.get(cache.markCacheKey + id);
        if(mark==null){
            mark = markRepository.getMarkById(id);
            cache.put(cache.markCacheKey + id, mark);
        }
        return mark;
    }
}
