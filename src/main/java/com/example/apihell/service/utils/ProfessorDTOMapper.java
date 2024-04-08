package com.example.apihell.service.utils;

import com.example.apihell.model.Professor;
import com.example.apihell.model.dto.ProfessorDTO;
import org.springframework.stereotype.Service;

@Service
public class ProfessorDTOMapper{
    private final  SubjectDTOMapper subjectDTOMapper;

    public ProfessorDTOMapper(SubjectDTOMapper subjectDTOMapper) {
        this.subjectDTOMapper = subjectDTOMapper;
    }

    public ProfessorDTO wrap(Professor professor){
        return new ProfessorDTO(
                professor.getSurname(),
                professor.getName(),
                professor.getPatronim(),
                professor.getTitle(),
                professor.getDepartment(),
                professor.getSubjects().stream().map(subjectDTOMapper::wrap).toList()
                );
    }

    public Professor unwrap(ProfessorDTO professorDTO){
        Professor professor = new Professor();

        professor.setName(professorDTO.name());
        professor.setSurname(professorDTO.surname());
        professor.setPatronim(professorDTO.patronim());
        professor.setTitle(professorDTO.title());
        professor.setDepartment(professorDTO.department());
        professor.setSubjects(professorDTO.subjects().stream().map(subjectDTOMapper::unwrap).toList());

        return professor;
    }
}
