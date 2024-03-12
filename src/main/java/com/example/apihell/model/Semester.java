package com.example.apihell.model;

import jakarta.persistence.*;

@Entity
@Table(name="sem")
public class Semester {
    @Id
    @GeneratedValue
    String id;
    @Column(name="subject")
    String subject;
    @Column(name="hours")
    String hours;
    @Column(name="lecturer")
    String lecturer;
    @Column(name="retries")
    int retries;
    @Column(name="spec")
    String spec;
    @Column(name="semester_number")
    int semesterNumber;

    @Column(name="student_id")
    String studentId;

    public Semester(String subject, String hours, String lecturer, int retries, String spec, int semesterNumber, String studentId) {
        this.subject = subject;
        this.hours = hours;
        this.lecturer = lecturer;
        this.retries = retries;
        this.spec = spec;
        this.semesterNumber = semesterNumber;
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Semester() {

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getSemesterNumber() {
        return semesterNumber;
    }

    public void setSemesterNumber(int semesterNumber) {
        this.semesterNumber = semesterNumber;
    }
}
