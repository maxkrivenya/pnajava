package com.example.apihell.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="skips")
public class Skip {

    @Id
    @Column(name="date")
    private String date;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="stud_id")
    private Student student;

    @Column(name="subject")
    private String subject;

    @Column(name="semester_number")
    private int semesterNumber;

    @Column(name="hours")
    private int hours;

    public int getHours() {
        return hours;
    }


    public void setHours(int hours) {
        this.hours = hours;
    }


    public Skip(Student student, String subject, int semesterNumber, String date, int hours) {
        this.student = student;
        this.subject = subject;
        this.semesterNumber = semesterNumber;
        this.date = date;
        this.hours = hours;
    }

    public Skip(String subject, int semesterNumber, String date, int hours) {
        this.subject = subject;
        this.semesterNumber = semesterNumber;
        this.date = date;
        this.hours = hours;
    }

    public Skip() {
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

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSemesterNumber() {
        return semesterNumber;
    }

    public void setSemesterNumber(int semesterNumber) {
        this.semesterNumber = semesterNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
