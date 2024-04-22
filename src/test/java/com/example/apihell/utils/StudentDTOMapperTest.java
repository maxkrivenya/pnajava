package com.example.apihell.utils;

import com.example.apihell.model.*;
import com.example.apihell.model.dto.MarkDTO;
import com.example.apihell.model.dto.StudentDTO;
import com.example.apihell.service.utils.MarkDTOMapper;
import com.example.apihell.service.utils.SkipDTOMapper;
import com.example.apihell.service.utils.StudentDTOMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentDTOMapperTest {


    @Before
    public void setUp() {
        MarkDTOMapper markDTOMapper = getMarkDTOMapper();
        SkipDTOMapper skipDTOMapper = mock(SkipDTOMapper.class);
        this.studentDTOMapper = new StudentDTOMapper(markDTOMapper, skipDTOMapper);
    }

    private StudentDTOMapper studentDTOMapper;


    private final static List<Mark> marks1 = new ArrayList<>(List.of(
            new Mark("1", "Date", 10,
                    new Student("id", "Surname", "Name", "Patronim", "groupExists"),
                    new Professor("profid", "profsur", "profname",
                            "profpatr", "proftitle", "profdep"),
                    new Subject("subjid", "shortName", "longName", "semid")
            ),
            new Mark("2", "Date", 4,
                    new Student("id", "Surname", "Name", "Patronim", "groupExists"),
                    new Professor("profid", "profsur", "profname",
                            "profpatr", "proftitle", "profdep"),
                    new Subject("subjid", "shortName", "longName", "semid")
            )
    ));

    private static List<MarkDTO> markDTOSExpected = new ArrayList<>(List.of(
            new MarkDTO(marks1.get(0).getValue(), marks1.get(0).getSubject().getName(), marks1.get(0).getDate()),
            new MarkDTO(marks1.get(1).getValue(), marks1.get(1).getSubject().getName(), marks1.get(1).getDate())
    ));

    private static List<Mark> marksExpected = new ArrayList<>(List.of(
            new Mark(markDTOSExpected.get(0).date(), markDTOSExpected.get(0).value()),
            new Mark(markDTOSExpected.get(1).date(), markDTOSExpected.get(1).value())
    ));

    private final static Student EXISTING_STUDENT
            = new Student("id", "Surname", "Name", "Patronim", "groupExists",
            marks1,
            new ArrayList<Skip>(),
            new Group()
    );

    private final Student EXPECTED_STUDENT = new Student(
            EXISTING_STUDENT_DTO.id(),
            EXISTING_STUDENT_DTO.surname(),
            EXISTING_STUDENT_DTO.name(),
            EXISTING_STUDENT_DTO.patronim(),
            EXISTING_STUDENT_DTO.group(),
            marksExpected,
            new ArrayList<>(),
            new Group()
    );

    private final static StudentDTO EXISTING_STUDENT_DTO = new StudentDTO(
            EXISTING_STUDENT.getId(),
            EXISTING_STUDENT.getSurname(),
            EXISTING_STUDENT.getName(),
            EXISTING_STUDENT.getPatronim(),
            EXISTING_STUDENT.getGroupId(),
            markDTOSExpected,
            new ArrayList<>()
    );

    @Before
    public void setup() {
        this.studentDTOMapper = new StudentDTOMapper(new MarkDTOMapper(), new SkipDTOMapper());
    }

    @Test
    public void wrapTest(){
        StudentDTO realStudentDTO = studentDTOMapper.wrap(EXISTING_STUDENT);
        assertEquals(EXISTING_STUDENT_DTO, realStudentDTO);
    }

    @Test
    public void unwrapTest(){
        StudentDTO realStudentDTO = studentDTOMapper.wrap(EXISTING_STUDENT);
        assertEquals(EXISTING_STUDENT_DTO, realStudentDTO);
        Student realUnwrapStudent = studentDTOMapper.unwrap(EXISTING_STUDENT_DTO);
        assertEquals(EXPECTED_STUDENT.getMarks(), realUnwrapStudent.getMarks());
        assertEquals(EXPECTED_STUDENT.getSurname(), realUnwrapStudent.getSurname());
        assertEquals(EXPECTED_STUDENT.getName(), realUnwrapStudent.getName());
        assertEquals(EXPECTED_STUDENT.getPatronim(), realUnwrapStudent.getPatronim());
        assertEquals(EXPECTED_STUDENT.getGroupId(), realUnwrapStudent.getGroupId());
    }

    private MarkDTOMapper getMarkDTOMapper() {
        MarkDTOMapper mock = mock(MarkDTOMapper.class);

        when(mock.wrap(marks1.get(0))).thenReturn(markDTOSExpected.get(0));
        when(mock.wrap(marks1.get(1))).thenReturn(markDTOSExpected.get(1));

        when(mock.unwrap(markDTOSExpected.get(0))).thenReturn(marksExpected.get(0));
        when(mock.unwrap(markDTOSExpected.get(1))).thenReturn(marksExpected.get(1));

        return mock;
    }
}