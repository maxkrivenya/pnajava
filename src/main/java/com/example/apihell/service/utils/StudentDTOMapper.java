package com.example.apihell.service.utils;

import com.example.apihell.model.Student;
import com.example.apihell.model.base.LectureResult;
import com.example.apihell.model.dto.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StudentDTOMapper{
    public StudentDTO wrap(Student student){
        return new StudentDTO(
          student.getId(),
          student.getSurname(),
          student.getName(),
          student.getPatronim(),
          student.getGroupId(),
          student.getMarks()
                 .stream()
                 .map(LectureResult::getValue)
                 .toList()
          ,
          student.getSkips()
                  .stream()
                  .map(LectureResult::getValue)
                  .toList()
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
