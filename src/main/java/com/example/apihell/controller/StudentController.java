package com.example.apihell.controller;

import com.example.apihell.model.Student;
import com.example.apihell.model.dto.MarkDTO;
import com.example.apihell.model.dto.SkipDTO;
import com.example.apihell.model.dto.StudentDTO;
import com.example.apihell.service.implementations.StudentServiceImpl;
import com.example.apihell.service.utils.MarkDTOMapper;
import com.example.apihell.service.utils.SkipDTOMapper;
import com.example.apihell.service.utils.StudentDTOMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/api/student")
@Transactional
@Validated
public class StudentController {
    private final StudentServiceImpl studentService;
    private final StudentDTOMapper studentDTOMapper;
    private final MarkDTOMapper markDTOMapper;
    private final SkipDTOMapper skipDTOMapper;

    public StudentController(StudentServiceImpl studentService, StudentDTOMapper studentDTOMapper, MarkDTOMapper markDTOMapper, SkipDTOMapper skipDTOMapper) {
        this.studentService = studentService;
        this.studentDTOMapper = studentDTOMapper;
        this.markDTOMapper = markDTOMapper;
        this.skipDTOMapper = skipDTOMapper;
    }

    @PostMapping(value = "/like/{id}")
    public ResponseEntity<List<StudentDTO>> likeStudent(@PathVariable int id, @RequestBody StudentDTO studentDTO) {
        if(studentDTO == null){return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        List<Student> allStudents = studentService.getAllStudents();
        if(allStudents.isEmpty()) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        List<StudentDTO> returnValue =  allStudents.stream()
                .map(studentDTOMapper::wrap)
                .filter(st -> st.id().contains(studentDTO.id()))
                .filter(st -> st.name().contains(studentDTO.name()))
                .filter(st -> st.surname().contains(studentDTO.surname()))
                .filter(st -> st.patronim().contains(studentDTO.patronim()))
                .filter(st -> st.groupId().contains(studentDTO.groupId()))
                .toList();
        if(returnValue.isEmpty()){return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
        if(returnValue.size() < 5){return new ResponseEntity<>(returnValue, HttpStatus.OK);}
        if(returnValue.size() < 5*(id + 1)){
            returnValue = returnValue.subList(returnValue.size() - 5, returnValue.size());
        }else{
            returnValue = returnValue.subList(5 * id, (5 * id) + 5);
        }

        return new ResponseEntity<>(returnValue, HttpStatus.OK);

    }

    @PutMapping(value="/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) {
        if(!studentService.studentExists(studentDTO.id())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);        }
        Student student = studentDTOMapper.unwrap(studentDTO);
        student.setId(studentDTO.id());
        return new ResponseEntity<>(studentDTOMapper.wrap(studentService.save(student)), HttpStatus.OK);
    }

    @PostMapping(value="/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> createStudent(@RequestBody StudentDTO studentDTO){
        Student student = studentDTOMapper.unwrap(studentDTO);
        studentService.save(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable(name="id") String id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok("deleted student " + id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") String id) {   
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(studentDTOMapper.wrap(student) , HttpStatus.OK);
        }
    }

    @GetMapping("/{id}/marks")
    public ResponseEntity<List<MarkDTO>> getStudentsMarksById(@PathVariable("id") String id) {
        Student student = studentService.getStudentById(id);
        if(student==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<MarkDTO> marks = new ArrayList<>();
        student.getMarks().forEach(mark -> marks.add(markDTOMapper.wrap(mark)));
        if (student.getMarks().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(marks, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}/skips")
    public ResponseEntity<List<SkipDTO>> getStudentsSkipsById(@PathVariable("id") String id) {
        Student student = studentService.getStudentById(id);
        if(student==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<SkipDTO> skips = new ArrayList<>();
        student.getSkips().forEach(skip -> skips.add(skipDTOMapper.wrap(skip)));
        if (student.getSkips().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(skips, HttpStatus.OK);
        }
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<List<StudentDTO>> getStudentsByGroupId(@PathVariable("id") String id) {
        List<Student> students = studentService.getStudentsByGroupId(id);
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(students.stream().map(studentDTOMapper::wrap).toList(), HttpStatus.OK);
        }
    }

    @PostMapping(value="/bulk/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> createMultipleStudents(@RequestBody List<StudentDTO> studentDTOList){
        studentDTOList.forEach(studentDTO -> studentService.save(studentDTOMapper.unwrap(studentDTO)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path="/sameSurname/{surnameLike}")
    public ResponseEntity<List<String>> getProfessorsByStudentId(@PathVariable(name = "surnameLike") String surnameLike){

        List<String> professors = studentService.getSameSurnameLike(surnameLike);

        if(professors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(professors, HttpStatus.OK);
    }

    @GetMapping(path = "/group/{groupId}/average")
    public ResponseEntity<ObjectNode> getAverageScoreInGroup(@PathVariable("groupId") String groupId) {
        OptionalDouble averageMark = studentService.getAverageScoreInGroup(groupId);
        if(averageMark.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObject = mapper.createObjectNode();
        jsonObject.put("group", groupId);
        jsonObject.put("averageMark", averageMark.getAsDouble());
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
}
