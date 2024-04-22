package com.example.apihell.utils;

import com.example.apihell.model.Mark;
import com.example.apihell.model.Professor;
import com.example.apihell.model.Student;
import com.example.apihell.model.Subject;
import com.example.apihell.model.dto.MarkDTO;
import com.example.apihell.model.dto.StudentDTO;
import com.example.apihell.service.utils.MarkDTOMapper;
import com.example.apihell.service.utils.SkipDTOMapper;
import com.example.apihell.service.utils.StudentDTOMapper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkDTOMapperTest {

    private MarkDTOMapper markDTOMapper;

    private final static Mark mark = new Mark("1", "Date", 10,
            new Student("id",
                    "Surname",
                    "Name",
                    "Patronim",
                    "groupExists"
            ),
            new Professor(
                    "profid",
                    "profsur",
                    "profname",
                    "profpatr",
                    "proftitle",
                    "profdep"
            ),
            new Subject(
                    "subjid",
                    "shortName",
                    "longName",
                    "semid"
            )
    );

    private final static MarkDTO markDTOExpected = new MarkDTO(
            10,
            "shortName",
            "Date"
    );

    private final static Mark markExpected = new Mark(
            "",
            "Date",
            10
    );

    @Before
    public void setup() {
        markDTOMapper = new MarkDTOMapper();
    }

    @Test
    public void wrapTest(){
        MarkDTO markDTO = markDTOMapper.wrap(mark);
        assertEquals(markDTOExpected, markDTO);
    }

    @Test
    public void unwrapTest(){
        Mark markUnwrapped = markDTOMapper.unwrap(markDTOExpected);
        assertEquals(markExpected.getDate(), markUnwrapped.getDate());
        assertEquals(markExpected.getValue(), markUnwrapped.getValue());
    }


}
