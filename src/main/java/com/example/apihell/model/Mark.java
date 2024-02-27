package com.example.apihell.model;

import jakarta.persistence.*;
@Table(name = "marks")
@Entity
public class Mark {
    @Column(name="id")
    private String studentId;
    @Column(name="mark")
    private Integer value;
    @Column(name="subj")
    private String subject;

    @Id
    @Column(name="date")
    private String date;
    @Column(name="type")
    private String type;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) { this.studentId = studentId;}

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
