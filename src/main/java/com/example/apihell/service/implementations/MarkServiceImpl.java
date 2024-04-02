package com.example.apihell.service.implementations;

import com.example.apihell.model.Mark;
import com.example.apihell.repository.MarkRepository;
import com.example.apihell.service.MarkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkServiceImpl implements MarkService {
    private final MarkRepository markRepository;
    public MarkServiceImpl(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public List<Mark> getMarksByStudentId(String id){
        return markRepository.getMarksByStudentId(id);
    }
    public Mark save(Mark mark){
        return markRepository.save(mark);
    }
    public void deleteMarkById(String id){
        markRepository.deleteMarkById(id);
    }
    public Mark getMarkById(String id) {
        return markRepository.getMarkById(id);
    }
}
