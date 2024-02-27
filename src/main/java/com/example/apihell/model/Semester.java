package com.example.apihell.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sem")
public class Semester {
    @Column(name="subj")
    String subject;
    @Column(name="hours")
    String hours;
    @Column(name="control")
    String control;

    @Column(name="lecturer")
    @Id
    String lecturer;
    @Column(name="retries")
    int retries;
    @Column(name="spec")
    String spec;
    @Column(name="semesterNumber")
    int semesterNumber;

    public Semester(String subject, String hours, String control, String lecturer, int retries, String spec, int semesterNumber) {
        this.subject = subject;
        this.hours = hours;
        this.control = control;
        this.lecturer = lecturer;
        this.retries = retries;
        this.spec = spec;
        this.semesterNumber = semesterNumber;
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

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
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
