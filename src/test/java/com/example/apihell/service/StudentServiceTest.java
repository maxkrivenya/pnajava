package com.example.apihell.service;
import com.example.apihell.model.Mark;
import com.example.apihell.model.Student;
import com.example.apihell.service.implementations.StudentServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    private StudentService studentService;

    private List<Mark> marks1;
    private List<Mark> marks2;

    private Student EXISTING_STUDENT;

    private Student EXISTING_STUDENT_GROUPMATE;

    private Student NO_STUDENT;

    private List<Student> groupmates;

    @Before
    public void setUp() throws Exception {

        marks1 = new ArrayList<>();
        marks2 = new ArrayList<>();

        marks1.add(new Mark("1", "Date", 10));
        marks1.add(new Mark("2", "Date", 4));
        marks2.add(new Mark("1", "Date", 9));

        EXISTING_STUDENT
                = new Student("id", "Surname", "Name", "Patronim", "groupExists");
        EXISTING_STUDENT_GROUPMATE
                = new Student("id2", "Surname2", "Name2", "Patronim2", "groupExists");
        NO_STUDENT =
                new Student("no id", "no Surname", "no Name", "no Patronim", "no groupExists");


        EXISTING_STUDENT.setMarks(marks1);
        EXISTING_STUDENT_GROUPMATE.setMarks(marks2);

        groupmates = new ArrayList<>();
        groupmates.add(EXISTING_STUDENT);
        groupmates.add(EXISTING_STUDENT_GROUPMATE);


        studentService = getStudentService();
    }

    @Test
    public void studentExistsExpectedTrue() throws Exception {
        boolean exists = studentService.studentExists(EXISTING_STUDENT.getId());
        assertTrue(exists);
    }

    @Test
    public void studentExistsExpectedFalse() throws Exception {
        boolean exists = studentService.studentExists(NO_STUDENT.getId());
        assertFalse(exists);
    }

    @Test
    public void getStudentByIdExpectedTrue() throws Exception {
        Student student = studentService.getStudentById(EXISTING_STUDENT.getId());
        assertEquals(EXISTING_STUDENT, student);
    }

    @Test
    public void getStudentByIdExpectedFalse() throws Exception {
        Student student = studentService.getStudentById(NO_STUDENT.getId());
        assertNull(student);
    }

    @Test
    public void groupTestExpectedTrue() throws Exception {
        List<Student> students = studentService.getStudentsByGroupId(EXISTING_STUDENT.getGroupId());
        assertEquals(groupmates, students);
    }

    @Test
    public void groupTestExpectedFalse() throws Exception {
        List<Student> students = studentService.getStudentsByGroupId(NO_STUDENT.getGroupId());
        assertEquals(Collections.emptyList(), students);
    }

    @Test
    public void saveExistingStudentExpectedTrue() throws Exception {
        Student student = EXISTING_STUDENT_GROUPMATE;
        Student existingStudent = studentService.getStudentById(EXISTING_STUDENT.getId());
        assertNotNull(existingStudent);
        Student savedStudent = studentService.save(student);
        assertEquals(student, savedStudent);
        assertNotEquals(existingStudent, savedStudent);
    }

    @Test
    public void getAverageScoreInGroupExpectedTrue(){
        int amt = 0;
        double expectedAverage = 0;

        for(Mark mark: marks1){
            amt++;
            expectedAverage += mark.getValue();
        }
        for(Mark mark: marks2){
            amt++;
            expectedAverage += mark.getValue();
        }
        expectedAverage /= amt;

        OptionalDouble realAverage = studentService
                .getStudentsByGroupId(EXISTING_STUDENT.getGroupId())
                .stream()
                .flatMap(student -> student.getMarks().stream())
                .mapToDouble(Mark::getValue)
                .average();

        assertNotNull(realAverage);

        assertEquals(expectedAverage, realAverage.getAsDouble(), 0);
    }

    @Test
    public void getAverageScoreInGroupExpectedFalse(){

        List<Student> caseNoGroup = studentService
                .getStudentsByGroupId(NO_STUDENT.getGroupId());

        assertEquals(Collections.emptyList(), caseNoGroup);
    }

    @Test
    public void saveNewStudentExpectedTrue() throws Exception {
        Student student = NO_STUDENT;
        Student existingStudent = studentService.getStudentById(student.getId());
        assertNull(existingStudent);
        Student savedStudent = studentService.save(student);
        assertEquals(student, savedStudent);
    }

    private StudentService getStudentService() {
        StudentService mock = mock(StudentServiceImpl.class);

        //studentExists
        when(mock.studentExists(EXISTING_STUDENT.getId())).thenReturn(true);
        when(mock.studentExists(NO_STUDENT.getId())).thenReturn(false);

        //getById
        when(mock.getStudentById(EXISTING_STUDENT.getId())).thenReturn(EXISTING_STUDENT);
        when(mock.getStudentById(NO_STUDENT.getId())).thenReturn(null);

        //getByGroupId
        when(mock.getStudentsByGroupId(EXISTING_STUDENT.getGroupId())).thenReturn(groupmates);
        when(mock.getStudentsByGroupId(NO_STUDENT.getGroupId())).thenReturn(Collections.emptyList());

        //save
        when(mock.save(any(Student.class))).then(returnsFirstArg());

        return mock;
    }

}
