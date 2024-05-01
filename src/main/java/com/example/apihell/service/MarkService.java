package com.example.apihell.service;

import com.example.apihell.model.Mark;

import java.util.List;

public interface MarkService {
    public List<Mark> getMarksByStudentId(String id);

    public Mark save(Mark mark);

    public void deleteMarkById(String id);

    public Mark getMarkById(String id);

}