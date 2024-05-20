package com.example.apihell.service;

import com.example.apihell.model.Group;

import java.util.List;

public interface GroupService {

    List<Group> findAll();
    public Group getGroupById(String id);

    public void deleteGroupById(String id);

    public Group save(Group group);

}
