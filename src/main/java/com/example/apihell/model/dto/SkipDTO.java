package com.example.apihell.model.dto;

import com.example.apihell.model.Professor;
import com.example.apihell.model.Student;
import com.example.apihell.model.Subject;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SkipDTO {
    @Data
    @Builder
    public class Response {
        private String id;
        private String date;
        private int value;
        private Boolean reasonable;
        private Student student;
        private Subject subject;
        private Professor professor;
    }
    @Data
    @Builder
    public class RequestBody {
        private String id;
        private String date;
        private int value;
        private Boolean reasonable;
    }
}
