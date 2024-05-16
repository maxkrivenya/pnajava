package com.example.apihell.service;
import com.example.apihell.components.CacheComponent;
import com.example.apihell.model.*;
import com.example.apihell.repository.MarkRepository;

import com.example.apihell.service.implementations.MarkServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class MarkServiceTest {

    private MarkServiceImpl markService;


    private final static List<Mark> marks1 = new ArrayList<>(List.of(
            new Mark("1", "Date", 10),
            new Mark("2", "Date", 4)
    ));

    private final static String NO_MARK_WITH_SUCH_ID = "832469236749";
    private final static Student EXISTING_STUDENT
            = new Student("id", "Surname", "Name", "Patronim", "groupExists",
            marks1, new ArrayList<>());

    private final static Student NO_STUDENT
            = new Student("no id", "no Surname", "no Name", "no Patronim", "no groupExists"
            , new ArrayList<>(), new ArrayList<>());


    @Before
    public void setUp() {
        markService = new MarkServiceImpl(
                getCacheComponent(),
                getMarkRepository()
        );
    }


    @Test
    public void getMarksByStudentIdExpectedTrue(){
        List<Mark> realMarks = markService.getMarksByStudentId(EXISTING_STUDENT.getId());
        assertEquals(marks1, realMarks);
    }

    @Test
    public void getMarksByStudentIdExpectedFalse(){
        List<Mark> realMarks = markService.getMarksByStudentId(NO_STUDENT.getId());
        assertEquals(Collections.emptyList(), realMarks);
    }

    @Test
    public void getMarkByIdExpectedTrue(){
        Mark mark = markService.getMarkById(marks1.get(0).getId());
        assertEquals(marks1.get(0), mark);
    }

    @Test
    public void getMarkByIdExpectedFalse(){
        Mark mark = markService.getMarkById(NO_MARK_WITH_SUCH_ID);
        assertNull(mark);
    }

    @Test
    public void deleteTest() {
        markService.deleteMarkById(marks1.get(0).getId());
    }

    private MarkRepository getMarkRepository() {

        MarkRepository mock = mock(MarkRepository.class);

        //getByStudentId
        when(mock.getMarksByStudentId(EXISTING_STUDENT.getId())).thenReturn(marks1);
        when(mock.getMarksByStudentId(NO_STUDENT.getId())).thenReturn(Collections.emptyList());

        //getById
        when(mock.getMarkById(marks1.get(0).getId())).thenReturn(marks1.get(0));
        when(mock.getMarkById(NO_MARK_WITH_SUCH_ID)).thenReturn(null);

        return mock;
    }

    private CacheComponent getCacheComponent() {

        return mock(CacheComponent.class);
    }

}
