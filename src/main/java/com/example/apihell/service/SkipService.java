package com.example.apihell.service;

import com.example.apihell.model.Skip;

import java.util.List;

public interface SkipService{
    public List<Skip> getSkipsByStudentId(String id);

    public Skip save(Skip skip);

    public Skip getSkipById(String id);

    public void deleteSkip(Skip skip);

    public void deleteSkipById(String id);
    public void logCache();
}
