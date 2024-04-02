package com.example.apihell.model.dto;

import com.example.apihell.model.Mark;
import com.example.apihell.model.Skip;
import com.example.apihell.model.Subject;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ProfessorDTO {
    @Data
    @Builder
    public class Response {
        private String id;
        private String surname;
        private String name;
        private String patronim;
        private List<Mark> marks;
        private List<Skip> skips;
        private List<Subject> subjects;
    }
    @Data
    @Builder
    public class RequestBody {
        private String id;
        private String surname;
        private String name;
        private String patronim;
    }
}
