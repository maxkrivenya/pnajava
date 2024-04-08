package com.example.apihell.model.dto;

import java.util.List;

public record StudentDTO(
        String id,
        String surname,
        String name,
        String patronim,
        String group,
        List<MarkDTO> marks,
        List<SkipDTO> skips
)
{

}