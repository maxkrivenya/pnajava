package com.example.apihell.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="marks")
public class Mark {
    @Id
    @Column(name="id")
    private String id;
    @Column(name="date")
    private String date;
    @Column(name="value")
    private Integer value;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student-id", referencedColumnName = "id")
    @JsonBackReference
    private Student student;

    /*
v
    @ManyToOne(fetch = FetchType.LAZY)
    private Professor professor;
    @JoinColumn(name="subject-id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;
*/
    public Mark(){}

    public Mark(String id, String date, Integer value) {
        this.id = id;
        this.date = date;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    /*
    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    */
}
