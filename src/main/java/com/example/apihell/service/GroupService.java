package com.example.apihell.service;

import com.example.apihell.model.Group;

public interface GroupService {
    public Group getGroupById(String id) ;
    public void deleteGroupById(String id);
    public Group save(Group group);
    public void logCache();
}
