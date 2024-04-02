package com.example.apihell.service.implementations;

import com.example.apihell.model.Group;
import com.example.apihell.repository.GroupRepository;
import com.example.apihell.service.GroupService;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
        private final GroupRepository groupRepository;
        public GroupServiceImpl(GroupRepository groupRepository) {
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
        public Group save(Group group){
            return groupRepository.save(group);
        }
    }

