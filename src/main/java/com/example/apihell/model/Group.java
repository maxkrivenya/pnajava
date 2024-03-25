package com.example.apihell.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group implements Serializable {
    @Id
    @Column(name="id")
    private String id;
    @Column(name="degree")
    private String degree;
    @Column(name="faculty")
    private String faculty;
    @Column(name="semester-number")
    private Integer semesterNumber;
    @Column(name="education-type")
    private String educationType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name="group-id")
    @JsonBackReference
    private List<Student> students;

    public Group(){
    }
    public Group(String id, String degree, String faculty, Integer semesterNumber, String educationType) {
        this.id = id;
        this.degree = degree;
        this.faculty = faculty;
        this.semesterNumber = semesterNumber;
        this.educationType = educationType;
    }

    public String getId() { return id; }
    public void setId(String id) {
        this.id = id;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getFaculty() {
        return faculty;
    }
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    public Integer getSemesterNumber() {
        return semesterNumber;
    }
    public void setSemesterNumber(Integer semesterNumber) {
        this.semesterNumber = semesterNumber;
    }
    public String getEducationType() {
        return educationType;
    }
    public void setEducationType(String educationType) {
        this.educationType = educationType;
    }
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
