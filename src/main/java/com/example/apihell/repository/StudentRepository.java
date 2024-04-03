package com.example.apihell.repository;

import com.example.apihell.model.Group;
import com.example.apihell.model.Student;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Nullable
    Student getStudentById(String id);
    List<Student> getStudentsByGroup(Group group);
    void deleteStudentById(String id);
    @Override
    Student save(Student student);

    @Query(value="with \n" +
            "find_group as(\n" +
            "\tselect * from groups g\n" +
            "\twhere g.id = '?1'\n" +
            "),\n" +
            "find_exams as (\n" +
            "\tselect e.* from exams e  \n" +
            "\tinner join find_group fg\n" +
            "\ton fg.id = e.\"group-id\"\n" +
            "),\n" +
            "find_subjects as (\n" +
            "\tselect s.* from subjects s  \n" +
            "\tleft join find_exams fe \n" +
            "\ton fe.\"subject-id\" = s.id\n" +
            "),\n" +
            "subj_to_prof as (\n" +
            "\tselect sp.* from \"subject-professor\" sp \n" +
            "\tright join find_subjects fs \n" +
            "\ton sp.\"subject-id\" = fs.id\n" +
            "),\n" +
            "find_professors as (\n" +
            "\tselect p.* from professors p  \n" +
            "\tinner join subj_to_prof stp on stp.\"professor-id\" = p.id\n" +
            ") \n" +
            "select fp.* from find_professors fp"
            ,
        nativeQuery = true
    )
    List<String> getProfessorsByStudentId(String id);
}
