package com.example.apihell.model;

import com.example.apihell.model.base.LectureResult;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="skips")
public class Skip extends LectureResult {
    @Column(name="reasonable")
    private Boolean reasonable;

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

    public Skip(String id, String date, Integer value, Boolean reasonable) {
        super(id,date,value);
        this.reasonable = reasonable;
    }

    public Skip() {super(); }

    public Boolean getReasonable() {
        return reasonable;
    }
    public void setReasonable(Boolean reasonable) {
        this.reasonable = reasonable;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public Professor getProfessor() { return professor;}
    public void setProfessor(Professor professor) { this.professor = professor; }
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }


}
