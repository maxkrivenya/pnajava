package com.example.apihell.model;

import com.example.apihell.model.base.LectureResult;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="marks")
public class Mark extends LectureResult {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student-id", referencedColumnName = "id")
    @JsonBackReference
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "professor-id", referencedColumnName = "id")
    @JsonBackReference
    private Professor professor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject-id", referencedColumnName = "id")
    @JsonBackReference
    private Subject subject;

    public Mark(){
        super();
    }

    public Mark(String date, Integer value){
        super(date, value);
        this.subject = new Subject();
        this.professor = new Professor();
    }

    public Mark(String id, String date, Integer value) {
        super(id,date,value);
        this.subject = new Subject();
        this.professor = new Professor();
    }

    public Mark(String id, String date, Integer value, Student student, Professor professor, Subject subject) {
        super(id, date, value);
        this.student = student;
        this.professor = professor;
        this.subject = subject;
    }



    public Student getStudent() { return student; }

    public void setStudent(Student student) { this.student = student; }

    public Professor getProfessor() { return professor; }

    public void setProfessor(Professor professor) { this.professor = professor; }

    public Subject getSubject() { return subject; }

    public void setSubject(Subject subject) { this.subject = subject; }
}
