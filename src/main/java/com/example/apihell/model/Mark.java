package com.example.apihell.model;

import com.example.apihell.base.LectureResult;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="marks")
public class Mark extends LectureResult {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student-id", referencedColumnName = "id")
    @JsonBackReference
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "professor-id", referencedColumnName = "id")
    @JsonBackReference
    private Professor professor;

    public Mark(){
        super();
    }
    public Mark(String id, String date, Integer value) {
        super(id,date,value);
    }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }
}
