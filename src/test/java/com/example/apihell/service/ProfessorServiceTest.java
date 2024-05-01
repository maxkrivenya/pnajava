package com.example.apihell.service;

import com.example.apihell.components.CacheComponent;
import com.example.apihell.model.Professor;
import com.example.apihell.repository.ProfessorRepository;
import com.example.apihell.service.implementations.ProfessorServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProfessorServiceTest {

    private final static String NO_PROFESSOR_WITH_SUCH_ID_EXISTS = "dhfjkshf";

    private final static Professor PROFESSOR = new Professor(
            "id",
            "surname",
            "name",
            "patronim",
            "title",
            "department"
    );

    private ProfessorServiceImpl professorService;

    @Before
    public void setUp() {
        professorService = new ProfessorServiceImpl(
                mock(CacheComponent.class),
                getProfessorRepository()
        );
    }

    @Test
    public void getProfessorByIdExpectedTrue(){
        Professor professor = professorService.getProfessorById(PROFESSOR.getId());
        assertEquals(PROFESSOR, professor);
    }

    @Test
    public void getProfessorByIdExpectedFalse(){
        Professor professor = professorService.getProfessorById(NO_PROFESSOR_WITH_SUCH_ID_EXISTS);
        assertNull(professor);
    }

    public ProfessorRepository getProfessorRepository(){
        ProfessorRepository mock = mock(ProfessorRepository.class);

        when(mock.getProfessorById(PROFESSOR.getId())).thenReturn(PROFESSOR);
        when(mock.getProfessorById(NO_PROFESSOR_WITH_SUCH_ID_EXISTS)).thenReturn(null);

        return mock;
    }
}
