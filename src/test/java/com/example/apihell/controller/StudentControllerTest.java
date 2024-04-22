package com.example.apihell.controller;

import com.example.apihell.model.*;
import com.example.apihell.model.dto.MarkDTO;
import com.example.apihell.model.dto.StudentDTO;
import com.example.apihell.service.StudentService;
import com.example.apihell.service.implementations.StudentServiceImpl;
import com.example.apihell.service.utils.MarkDTOMapper;
import com.example.apihell.service.utils.SkipDTOMapper;
import com.example.apihell.service.utils.StudentDTOMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import com.example.apihell.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentControllerTest {

    private StudentController studentController;

    private final static String NO_ID_EXISTS = "NO_ID";

    private final static List<Mark> marks1 = new ArrayList<>(List.of(
            new Mark("1", "Date", 10,
                    new Student("id", "Surname", "Name", "Patronim", "groupExists"),
                    new Professor("profid", "profsur", "profname",
                            "profpatr", "proftitle", "profdep"),
                    new Subject("subjid", "shortName", "longName", "semid")
            ),
            new Mark("2", "Date", 4,
                    new Student("id", "Surname", "Name", "Patronim", "groupExists"),
                    new Professor("profid", "profsur", "profname",
                            "profpatr", "proftitle", "profdep"),
                    new Subject("subjid", "shortName", "longName", "semid")
            )
    ));

    private static List<MarkDTO> markDTOSExpected = new ArrayList<>(List.of(
            new MarkDTO(marks1.get(0).getValue(), marks1.get(0).getSubject().getName(), marks1.get(0).getDate()),
            new MarkDTO(marks1.get(1).getValue(), marks1.get(1).getSubject().getName(), marks1.get(1).getDate())
    ));

    private static List<Mark> marksExpected = new ArrayList<>(List.of(
            new Mark(markDTOSExpected.get(0).date(), markDTOSExpected.get(0).value()),
            new Mark(markDTOSExpected.get(1).date(), markDTOSExpected.get(1).value())
    ));

    private final static Student EXISTING_STUDENT
            = new Student("id", "Surname", "Name", "Patronim", "groupExists",
            marks1,
            new ArrayList<Skip>(),
            new Group()
    );

    private final Student EXPECTED_STUDENT = new Student(
            EXISTING_STUDENT_DTO.id(),
            EXISTING_STUDENT_DTO.surname(),
            EXISTING_STUDENT_DTO.name(),
            EXISTING_STUDENT_DTO.patronim(),
            EXISTING_STUDENT_DTO.group(),
            marksExpected,
            new ArrayList<>(),
            new Group()
    );

    private final static StudentDTO EXISTING_STUDENT_DTO = new StudentDTO(
            EXISTING_STUDENT.getId(),
            EXISTING_STUDENT.getSurname(),
            EXISTING_STUDENT.getName(),
            EXISTING_STUDENT.getPatronim(),
            EXISTING_STUDENT.getGroupId(),
            markDTOSExpected,
            new ArrayList<>()
    );

    @Before
    public void setUp() {
    studentController = new StudentController(
            getStudentService(),
            getStudentDTOMapper(),
            getMarkDTOMapper(),
            getSkipDTOMapper()
        );
    }

    @Test
    public void getStudentByIdExpectedTrue() {
        ResponseEntity<StudentDTO> response = studentController.getStudentById(EXISTING_STUDENT.getId());
        assertEquals(new ResponseEntity<>(EXISTING_STUDENT_DTO,HttpStatus.OK), response);
    }

    @Test
    public void getStudentByIdExpectedFalse() {
        ResponseEntity<StudentDTO> response = studentController.getStudentById(NO_ID_EXISTS);
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND), response);
    }

    @Test
    public void getStudentsMarksById() {
        assertEquals(
                new ResponseEntity<>(
                        markDTOSExpected,
                        HttpStatus.OK
                ),
                studentController.getStudentsMarksById(
                        EXISTING_STUDENT.getId()
                )
        );
    }
/*
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
        public ResponseEntity<StudentDTO> updateStudent(@PathVariable String id, @RequestBody StudentDTO studentDTO) {
            if(!studentService.studentExists(id)){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);        }
            Student student = studentDTOMapper.unwrap(studentDTO);
            student.setId(id);
            return new ResponseEntity<>(studentDTOMapper.wrap(studentService.save(student)), HttpStatus.OK);
        }

        @DeleteMapping(path = "/delete/{id}")
        public ResponseEntity<String> deleteGroupById(@PathVariable(name="id") String id) {
            studentService.deleteStudentById(id);
            return ResponseEntity.ok("deleted student " + id);
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
        public ResponseEntity<Double> getAverageScoreInGroup(@PathVariable("groupId") String groupId) {
            OptionalDouble averageMark = studentService.getAverageScoreInGroup(groupId);
            if(averageMark.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(averageMark.getAsDouble(), HttpStatus.OK);
        }

 */
        public StudentDTOMapper getStudentDTOMapper(){
            StudentDTOMapper mock = mock(StudentDTOMapper.class);

            when(mock.unwrap(EXISTING_STUDENT_DTO)).thenReturn(EXPECTED_STUDENT);
            when(mock.wrap(EXISTING_STUDENT)).thenReturn(EXISTING_STUDENT_DTO);

            return mock;
        }

    public StudentService getStudentService(){
        StudentServiceImpl mock = mock(StudentServiceImpl.class);

        when(mock.getStudentById(EXISTING_STUDENT.getId())).thenReturn(EXISTING_STUDENT);
        when(mock.getStudentById(NO_ID_EXISTS)).thenReturn(null);


        return mock;
    }

    public MarkDTOMapper getMarkDTOMapper(){
        MarkDTOMapper mock = mock(MarkDTOMapper.class);

        when(mock.wrap(marks1.get(0))).thenReturn(markDTOSExpected.get(0));
        when(mock.wrap(marks1.get(1))).thenReturn(markDTOSExpected.get(1));

        when(mock.unwrap(markDTOSExpected.get(0))).thenReturn(marksExpected.get(0));
        when(mock.unwrap(markDTOSExpected.get(1))).thenReturn(marksExpected.get(1));

        return mock;
    }

    public SkipDTOMapper getSkipDTOMapper(){
        SkipDTOMapper mock = mock(SkipDTOMapper.class);
        return mock;
    }
    }
