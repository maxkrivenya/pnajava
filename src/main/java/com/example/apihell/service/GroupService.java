package com.example.apihell.service;

import com.example.apihell.model.Group;
import com.example.apihell.repository.GroupRepository;
import jakarta.xml.ws.WebFault;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group getGroupById(String id){
        return groupRepository.getGroupById(id);
    }

    public Group getGroupByFacultyAndDegreeAndAndEducationTypeAndSemesterNumber(String faculty,
                                                                                String degree,
                                                                                String educationType,
                                                                                int semesterNumber
    ){
        return groupRepository.getGroupByFacultyAndDegreeAndAndEducationTypeAndSemesterNumber(
                faculty,
                degree,
                educationType,
                semesterNumber
        );
    }

    public void deleteGroupById(String id){
        groupRepository.deleteById(id);
    }
}
