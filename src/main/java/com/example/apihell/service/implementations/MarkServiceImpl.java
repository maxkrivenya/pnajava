package com.example.apihell.service.implementations;

import com.example.apihell.components.CacheComponent;
import com.example.apihell.model.Mark;
import com.example.apihell.repository.MarkRepository;
import com.example.apihell.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MarkServiceImpl implements MarkService {

    @Autowired
    private final MarkRepository markRepository;
    private final CacheComponent cache;

    @Autowired
    public MarkServiceImpl(CacheComponent cache, MarkRepository markRepository) {
        this.cache = cache;
        this.markRepository = markRepository;
    }

    public List<Mark> getMarksByStudentId(String id){
        List<Mark> marks;
        String cacheKey = CacheComponent.MULTI_CACHE_KEY + CacheComponent.MARK_CACHE_KEY + id;
        marks = (List<Mark>) cache.get(cacheKey);
        if(marks == null){
            marks =  markRepository.getMarksByStudentId(id);
            cache.put(cacheKey, marks);
        }
        return marks;
    }

    public Mark save(Mark mark){
        cache.remove(CacheComponent.MARK_CACHE_KEY + mark.getId());
        cache.put(CacheComponent.MARK_CACHE_KEY + mark.getId(),mark);
        return markRepository.save(mark);
    }

    public void deleteMarkById(String id){
        cache.remove(CacheComponent.MARK_CACHE_KEY + id);
        markRepository.deleteMarkById(id);
    }

    public Mark getMarkById(String id) {
        Mark mark = (Mark) cache.get(CacheComponent.MARK_CACHE_KEY + id);
        if(mark==null){
            mark = markRepository.getMarkById(id);
            cache.put(CacheComponent.MARK_CACHE_KEY + id, mark);
        }
        return mark;
    }

    public void logCache(){
        cache.log();
    }
}
