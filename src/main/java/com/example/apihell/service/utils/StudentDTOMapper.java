package com.example.apihell.service.utils;

import com.example.apihell.model.Student;
import com.example.apihell.model.dto.StudentDTO;
import org.springframework.stereotype.Service;

@Service
public class StudentDTOMapper{
    private final  MarkDTOMapper markDTOMapper;
    private final  SkipDTOMapper skipDTOMapper;
    public StudentDTOMapper(MarkDTOMapper markDTOMapper, SkipDTOMapper skipDTOMapper) {
        this.markDTOMapper = markDTOMapper;
        this.skipDTOMapper = skipDTOMapper;
    }

    public StudentDTO wrap(Student student){
        return new StudentDTO(
          student.getId(),
          student.getSurname(),
          student.getName(),
          student.getPatronim(),
          student.getGroupId(),
          student.getMarks().stream().map(markDTOMapper::wrap).toList(),
          student.getSkips().stream().map(skipDTOMapper::wrap).toList()
        );
    }
    public Student unwrap(StudentDTO studentDTO){
        Student student = new Student();

        student.setId(studentDTO.id());
        student.setGroupId(studentDTO.group());
        student.setName(studentDTO.name());
        student.setSurname(studentDTO.surname());
        student.setPatronim(studentDTO.patronim());

        return student;
    }
}
