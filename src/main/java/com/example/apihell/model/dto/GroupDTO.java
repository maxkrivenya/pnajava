package com.example.apihell.model.dto;

public record GroupDTO(
        String id,
        String degree,
        String faculty,
        Integer semesterNumber,
        String educationType
) {
}
