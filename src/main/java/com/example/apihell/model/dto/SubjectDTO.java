package com.example.apihell.model.dto;

import com.example.apihell.model.*;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class SubjectDTO {
    @Data
    @Builder
    public class Response {
        private String id;
        private String name;
        private String fullName;
        private String semesterId;
        private List<Mark> marks;
        private List<Skip> skips;
        private List<Group> groups;
        private List<Professor> professors;
    }
    @Data
    @Builder
    public class RequestBody {
        private String id;
        private String name;
        private String fullName;
        private String patronim;
        private String semesterId;
    }
}
