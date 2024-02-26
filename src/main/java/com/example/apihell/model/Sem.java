package com.example.apihell.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sem")
public class Sem {
    @Column(name="subj")
    String subj;
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
    @Column(name="semnum")
    int semnum;

    public Sem(String subj, String hours, String control, String lecturer, int retries, String spec, int semnum) {
        this.subj = subj;
        this.hours = hours;
        this.control = control;
        this.lecturer = lecturer;
        this.retries = retries;
        this.spec = spec;
        this.semnum = semnum;
    }

    public Sem() {

    }

    public String getSubj() {
        return subj;
    }

    public void setSubj(String subj) {
        this.subj = subj;
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

    public int getSemnum() {
        return semnum;
    }

    public void setSemnum(int semnum) {
        this.semnum = semnum;
    }
}
