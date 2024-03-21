package com.example.apihell.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
@Table(name = "marks")
@Entity
public class Mark {

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="student_id")
    private Student student;

    @Column(name="mark")
    private Integer value;

    @Column(name="subject")
    private String subject;

    @Id
    @Column(name="date")
    private String date;

    public Mark(Student student, Integer value, String subject, String date, String type, String professor) {
        this.student = student;
        this.value = value;
        this.subject = subject;
        this.date = date;
        this.type = type;
        this.professor = professor;
    }

    public Mark(){}

    @Column(name="type")
    private String type;

    @Column(name="professor")
    private String professor;

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subj) {
        this.subject = subj;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
