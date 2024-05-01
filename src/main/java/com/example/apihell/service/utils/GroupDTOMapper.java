package com.example.apihell.service.utils;

import com.example.apihell.model.Group;
import com.example.apihell.model.dto.GroupDTO;
import org.springframework.stereotype.Service;

@Service
public class GroupDTOMapper{
    
    public GroupDTOMapper(SubjectDTOMapper subjectDTOMapper) {
    }

    public GroupDTO wrap(Group group){
        return new GroupDTO(
                group.getId(),
                group.getDegree(),
                group.getFaculty(),
                group.getSemesterNumber(),
                group.getEducationType()
        );
    }

    public Group unwrap(GroupDTO groupDTO){

        Group group = new Group();

        group.setId(groupDTO.id());
        group.setDegree(groupDTO.degree());
        group.setFaculty(groupDTO.faculty());
        group.setSemesterNumber(groupDTO.semesterNumber());
        group.setEducationType(groupDTO.educationType());

        return group;
    }
}
