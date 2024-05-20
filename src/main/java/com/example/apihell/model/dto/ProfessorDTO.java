package com.example.apihell.model.dto;

import java.util.List;

public record ProfessorDTO(
        String id,
        String surname,
        String name,
        String patronim,
        String title,
        String department,
        List<SubjectDTO> subjects
)
{

}