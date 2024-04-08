package com.example.apihell.controller;

import com.example.apihell.exception.ResourceNotFoundException;
import com.example.apihell.model.Professor;
import com.example.apihell.model.Student;
import com.example.apihell.model.dto.MarkDTO;
import com.example.apihell.model.dto.ProfessorDTO;
import com.example.apihell.model.dto.SkipDTO;
import com.example.apihell.model.dto.StudentDTO;
import com.example.apihell.service.StudentService;
import com.example.apihell.service.utils.MarkDTOMapper;
import com.example.apihell.service.utils.ProfessorDTOMapper;
import com.example.apihell.service.utils.SkipDTOMapper;
import com.example.apihell.service.utils.StudentDTOMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/student")
@Transactional
public class StudentController {
    private final StudentService studentService;
    private final StudentDTOMapper studentDTOMapper;
    private final MarkDTOMapper markDTOMapper;
    private final SkipDTOMapper skipDTOMapper;
    private final ProfessorDTOMapper professorDTOMapper;

    public StudentController(StudentService studentService, StudentDTOMapper studentDTOMapper, MarkDTOMapper markDTOMapper, SkipDTOMapper skipDTOMapper, ProfessorDTOMapper professorDTOMapper) {
        this.studentService = studentService;
        this.studentDTOMapper = studentDTOMapper;
        this.markDTOMapper = markDTOMapper;
        this.skipDTOMapper = skipDTOMapper;
        this.professorDTOMapper = professorDTOMapper;
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
    public ResponseEntity<List<Student>> getStudentsByGroupId(@PathVariable("id") String id) {
        List<Student> students = studentService.getStudentsByGroupId(id);
        if (students.isEmpty()) {
            return new ResponseEntity<>(students, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
    }

    @PostMapping(value="/new/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> createStudent(@RequestBody StudentDTO studentDTO){
        Student student = studentDTOMapper.unwrap(studentDTO);
        studentService.save(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping(value="/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable String id, @RequestBody StudentDTO studentDTO){
        if(!studentService.studentExists(id)){
           throw new ResourceNotFoundException("no such student!");
        }
        Student student = studentDTOMapper.unwrap(studentDTO);
        student.setId(id);
        return new ResponseEntity<>(studentDTOMapper.wrap(studentService.save(student)), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable(name="id") String id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok("deleted student " + id);
    }

    @GetMapping(path="/sameSurname")
    public ResponseEntity<List<String>> getProfessorsByStudentId(){

        List<String> professors = studentService.getSameSurname();

        if(professors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(professors, HttpStatus.OK);
    }

    @GetMapping(path="/cache")
    public HttpStatus logCache(){
        studentService.logCache();
        return HttpStatus.NO_CONTENT;
    }
}
