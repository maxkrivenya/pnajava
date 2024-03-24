package com.example.apihell.model;

import jakarta.persistence.*;

@Entity
@Table(name="skips")
public class Skip {
    @Id
    @Column(name="id")
    private String id;
    @Column(name="date")
    private String date;
    @Column(name="value")
    private Integer value;
    @Column(name="reasonable")
    private Boolean reasonable;

    @JoinColumn(name="student-id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    /*
    @JoinColumn(name = "professor-id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Professor professor;
    @JoinColumn(name="subject-id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;
   */

    public Skip(){}
    public Skip(String id, String date, Integer value, Boolean reasonable) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.reasonable = reasonable;
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
