package com.example.apihell.service;
import com.example.apihell.components.CacheComponent;
import com.example.apihell.model.*;
import com.example.apihell.repository.SubjectRepository;
import com.example.apihell.service.implementations.SubjectServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class SubjectServiceTest {

    private SubjectServiceImpl subjectService;

    private final static String nameExists = "nameExists";
    private final static String noSuchNameExists = "lsdahjkdgashijdfgsdhj";

    private final static String noSuchIdExists = "skadnkjasfgh";

    private final static Subject SAME_NAME_SUBJECT1
            = new Subject("id1", "name1", "fullName1", "semesterId1");

    private final static Subject SAME_NAME_SUBJECT2
            = new Subject("id2", "name2", "fullName2", "semesterId2");

    private final static Subject DIFFERENT_NAME_SUBJECT3
            = new Subject("id3", "n*ame1", "fullName3", "semesterId3");

    private final static Subject DOESNT_EXIST_SUBJECT4
            = new Subject("id4", "name4", "fullName4", "semesterId4");

    private final static List<Subject> sameNameLikeSubjects = new ArrayList<>(
            List.of(
                    SAME_NAME_SUBJECT1,
                    SAME_NAME_SUBJECT2
            )
    );


    @Before
    public void setUp() {
        subjectService = new SubjectServiceImpl(
                getCacheComponent(),
                getSubjectRepository()
        );
    }

    @Test
    public void getAllByNameTestExpectedTrue(){
        List<Subject> subjectsReal = subjectService.getAllByName(nameExists);
        assertEquals(sameNameLikeSubjects, subjectsReal);
    }

    @Test
    public void getAllByNameTestExpectedFalse(){
        List<Subject> subjectsReal = subjectService.getAllByName(noSuchNameExists);
        assertEquals(Collections.emptyList(), subjectsReal);
    }

    @Test
    public void getSubjectByIdExpectedTrue(){
        Subject expectedSubject1 = subjectService.getSubjectById(SAME_NAME_SUBJECT1.getId());
        Subject expectedSubject2 = subjectService.getSubjectById(SAME_NAME_SUBJECT2.getId());
        assertEquals(SAME_NAME_SUBJECT1, expectedSubject1);
        assertEquals(SAME_NAME_SUBJECT2, expectedSubject2);
    }

    @Test
    public void getSubjectByIdExpectedFalse(){
        Subject expectedNoSubject = subjectService.getSubjectById(noSuchIdExists);
        assertNull(expectedNoSubject);
    }

    @Test
    public void deleteTest() {
        subjectService.deleteSubjectById(SAME_NAME_SUBJECT1.getId());
    }

    @Test
    public void saveNewSubjectExpectedTrue(){
        Subject subject = DOESNT_EXIST_SUBJECT4;
        Subject existingSubject = subjectService.getSubjectById(subject.getId());
        assertNull(existingSubject);
        Subject savedSubject = subjectService.save(subject);
        assertEquals(subject, savedSubject);
    }

    private SubjectRepository getSubjectRepository() {

        SubjectRepository mock = mock(SubjectRepository.class);

        //getAllWhereNameLike
        when(mock.getAllByName(nameExists)).thenReturn(sameNameLikeSubjects);
        when(mock.getAllByName(noSuchNameExists)).thenReturn(Collections.emptyList());

        //getSubjectById
        when(mock.getSubjectById(SAME_NAME_SUBJECT1.getId())).thenReturn(SAME_NAME_SUBJECT1);
        when(mock.getSubjectById(SAME_NAME_SUBJECT2.getId())).thenReturn(SAME_NAME_SUBJECT2);
        when(mock.getSubjectById(DOESNT_EXIST_SUBJECT4.getId())).thenReturn(null);
        when(mock.getSubjectById(noSuchIdExists)).thenReturn(null);
        when(mock.save(DOESNT_EXIST_SUBJECT4)).thenReturn(DOESNT_EXIST_SUBJECT4);

        return mock;
    }

    private CacheComponent getCacheComponent() {

        return mock(CacheComponent.class);
    }

}
