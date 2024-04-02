package com.example.apihell.model.dto;

import com.example.apihell.model.*;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MarkDTO {
    @Data
    @Builder
    public class Response {
        private String id;
        private String date;
        private int value;
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
    }
}
