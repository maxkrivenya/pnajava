package com.example.apihell.service;
import com.example.apihell.components.CacheComponent;
import com.example.apihell.model.*;
import com.example.apihell.repository.GroupRepository;
import com.example.apihell.repository.StudentRepository;
import com.example.apihell.service.implementations.GroupServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class GroupServiceTest {

    private GroupServiceImpl groupService;

    private final static List<Mark> marks1 = new ArrayList<>(List.of(
            new Mark("1", "Date", 10),
            new Mark("2", "Date", 4)
    ));


    private final static Student EXISTING_STUDENT
            = new Student("id", "Surname", "Name", "Patronim", "groupExists",
            marks1, new ArrayList<Skip>());


    private final static Group group = new Group(
            EXISTING_STUDENT.getGroupId(),
            "degree",
            "faculty",
            1,
            "educationType"
    );
/*
    private final static List<Subject> subjects = new ArrayList<>(List.of(
        new Subject("subjectId1",
                "subjectName1",
                "subjectFullName1",
                "subjectSemesterId1"
        ),
            new Subject("subjectId2",
                    "subjectName2",
                    "subjectFullName2",
                    "subjectSemesterId2"
            )
    ));
*/
    @Before
    public void setUp() {
        groupService = new GroupServiceImpl(
                getGroupRepository(),
                getCacheComponent()
        );
    }

    @Test
    public void getGroupByIdExpectedTrue(){
        Group group1 = groupService.getGroupById(group.getId());
        assertEquals(group, group1);
    }

    @Test
    public void getGroupByIdExpectedFalse(){
        Group group1 = groupService.getGroupById("no such group should exist");
        assertNull(group1);
    }

    private GroupRepository getGroupRepository() {
        GroupRepository mock = mock(GroupRepository.class);

        when(mock.getGroupById(group.getId())).thenReturn(group);
        when(mock.getGroupById("no such group should exist")).thenReturn(null);

        return mock;
    }

    private CacheComponent getCacheComponent() {

        return mock(CacheComponent.class);
    }

}
