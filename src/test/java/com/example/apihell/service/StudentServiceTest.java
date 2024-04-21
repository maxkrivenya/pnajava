package com.example.apihell.service;
import com.example.apihell.components.CacheComponent;
import com.example.apihell.model.Group;
import com.example.apihell.model.Mark;
import com.example.apihell.model.Skip;
import com.example.apihell.model.Student;
import com.example.apihell.repository.GroupRepository;
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

    private StudentServiceImpl studentService;

    private final static List<Mark> marks1 = new ArrayList<>(List.of(
            new Mark("1", "Date", 10),
            new Mark("2", "Date", 4)
    ));

    private final static List<Mark> marks2 = new ArrayList<>(List.of(
            new Mark("1", "Date", 9)
    ));

    private final static Student EXISTING_STUDENT
            = new Student("id", "Surname", "Name", "Patronim", "groupExists",
            marks1, new ArrayList<Skip>(),new Group());
    private final static Student EXISTING_STUDENT_GROUPMATE
            = new Student("id2", "Surname2", "Name2", "Patronim2", "groupExists",
            marks2, new ArrayList<Skip>(), new Group());
    private final static Student NO_STUDENT
            = new Student("no id", "no Surname", "no Name", "no Patronim", "no groupExists"
    , new ArrayList<>(), new ArrayList<>(), new Group());


    private final static List<Student> groupmates = new ArrayList<>(
            List.of(
                    EXISTING_STUDENT,
                    EXISTING_STUDENT_GROUPMATE
            )
    );

    private final static Group group = new Group(
            EXISTING_STUDENT.getGroupId(),
            "degree",
            "faculty",
            1,
            "educationType"
    );

    @Before
    public void setUp() {
        studentService = new StudentServiceImpl(
                getCacheComponent(),
                getStudentRepository(),
                getGroupRepository()
        );
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
        assertNull(students);
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

        OptionalDouble realAverage = studentService.getAverageScoreInGroup(EXISTING_STUDENT.getGroupId());

        assertNotNull(realAverage);
        assertEquals(expectedAverage, realAverage.getAsDouble(), 0);
    }

    @Test
    public void getAverageScoreInGroupExpectedFalse(){
        OptionalDouble averageReal = studentService.getAverageScoreInGroup(NO_STUDENT.getGroupId());
        assertFalse(averageReal.isPresent());
    }

    @Test
    public void saveNewStudentExpectedTrue() throws Exception {
        Student student = NO_STUDENT;
        Student existingStudent = studentService.getStudentById(student.getId());
        assertNull(existingStudent);
        Student savedStudent = studentService.save(student);
        assertEquals(student, savedStudent);
    }


    @Test
    public void getSameSurnameExpectedTrue(){
        String queryParam = EXISTING_STUDENT.getSurname();

        List<String> expected = new ArrayList<>(List.of(EXISTING_STUDENT.getSurname()));
        List<String> real = studentService.getSameSurnameLike(queryParam);

        assertEquals(expected, real);
    }

    @Test
    public void getSameSurnameExpectedFalse(){
        String queryParam  = NO_STUDENT.getSurname();

        List<String> real = studentService.getSameSurnameLike(queryParam);

        assertEquals(Collections.emptyList(), real);
    }

    private StudentService getStudentRepository() {

        StudentService mock = mock(StudentService.class);

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

        //average mark in group
        when(mock.getAverageScoreInGroup(EXISTING_STUDENT.getGroupId())).thenReturn(
                groupmates.stream()
                .flatMap(student -> student.getMarks()
                        .stream())
                        .mapToDouble(Mark::getValue)
                        .average()
        );

        when(mock.getAverageScoreInGroup(NO_STUDENT.getGroupId())).thenReturn(OptionalDouble.empty());

        //sameSurnameLike
        when(mock.getSameSurnameLike("%" + EXISTING_STUDENT.getSurname() + "%"))
                .thenReturn(new ArrayList<>(List.of(EXISTING_STUDENT.getSurname())));

        when(mock.getSameSurnameLike("%" + NO_STUDENT.getSurname() + "%"))
                .thenReturn(Collections.emptyList());

        return mock;
    }
    private GroupRepository getGroupRepository() {
        GroupRepository mock = mock(GroupRepository.class);
        return mock;
    }
    private CacheComponent getCacheComponent() {
        CacheComponent mock = mock(CacheComponent.class);

        when(mock.get("group" + EXISTING_STUDENT.getGroupId())).thenReturn(group);
        when(mock.get("group" + NO_STUDENT.getGroupId())).thenReturn(null);
        return mock;
    }

}
