package com.example.apihell.service;
import com.example.apihell.components.CacheComponent;
import com.example.apihell.model.*;
import com.example.apihell.repository.SkipRepository;

import com.example.apihell.service.implementations.SkipServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class SkipServiceTest {

    private SkipServiceImpl skipService;


    private final static List<Skip> skips1 = new ArrayList<>(List.of(
            new Skip("1", "Date", 10, false),
            new Skip("2", "Date", 4, false)
    ));

    private final static String NO_SKIP_WITH_SUCH_ID = "832469236749";
    private final static Student EXISTING_STUDENT
            = new Student("id", "Surname", "Name", "Patronim", "groupExists",
            new ArrayList<>(), skips1);

    private final static Student NO_STUDENT
            = new Student("no id", "no Surname", "no Name", "no Patronim", "no groupExists"
            , new ArrayList<>(), new ArrayList<>());


    @Before
    public void setUp() {
        skipService = new SkipServiceImpl(
                getCacheComponent(),
                getSkipRepository()
        );
    }


    @Test
    public void getSkipsByStudentIdExpectedTrue(){
        List<Skip> realSkips = skipService.getSkipsByStudentId(EXISTING_STUDENT.getId());
        assertEquals(skips1, realSkips);
    }

    @Test
    public void getSkipsByStudentIdExpectedFalse(){
        List<Skip> realSkips = skipService.getSkipsByStudentId(NO_STUDENT.getId());
        assertEquals(Collections.emptyList(), realSkips);
    }

    @Test
    public void getSkipByIdExpectedTrue(){
        Skip skip = skipService.getSkipById(skips1.get(0).getId());
        assertEquals(skips1.get(0), skip);
    }

    @Test
    public void getSkipByIdExpectedFalse(){
        Skip skip = skipService.getSkipById(NO_SKIP_WITH_SUCH_ID);
        assertNull(skip);
    }

    private SkipRepository getSkipRepository() {

        SkipRepository mock = mock(SkipRepository.class);

        //getByStudentId
        when(mock.getSkipsByStudentId(EXISTING_STUDENT.getId())).thenReturn(skips1);
        when(mock.getSkipsByStudentId(NO_STUDENT.getId())).thenReturn(Collections.emptyList());

        //getById
        when(mock.getSkipById(skips1.get(0).getId())).thenReturn(skips1.get(0));
        when(mock.getSkipById(NO_SKIP_WITH_SUCH_ID)).thenReturn(null);

        return mock;
    }

    private CacheComponent getCacheComponent() {

        return mock(CacheComponent.class);
    }

}
