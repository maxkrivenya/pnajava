package com.example.apihell.service.utils;

import com.example.apihell.model.Student;
import com.example.apihell.model.Subject;
import com.example.apihell.model.dto.StudentDTO;
import com.example.apihell.model.dto.SubjectDTO;
import org.springframework.stereotype.Service;

@Service
public class SubjectDTOMapper{

    public SubjectDTO wrap(Subject subject){
        return new SubjectDTO(
                subject.getName(),
                subject.getFullName(),
                subject.getSemesterId()
        );
    }
    public Subject unwrap(SubjectDTO subjectDTO){
        Subject subject = new Subject();

        subject.setName(subjectDTO.name());
        subject.setFullName(subjectDTO.fullName());
        subject.setSemesterId(subjectDTO.semesterId());
        return subject;
    }
}
