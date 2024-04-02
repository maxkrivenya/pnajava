package com.example.apihell.model.dto;

import com.example.apihell.model.*;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class GroupDTO {
    @Data
    @Builder
    public class Response {
        private String id;
        private String semesterId;
        private String faculty;
        private String degree;
        private String educationType;
        private List<Student> students;
        private List<Subject> subjects;
    }
    @Data
    @Builder
    public class RequestBody {
        private String id;
        private String semesterId;
        private String faculty;
        private String degree;
        private String educationType;
    }
}
