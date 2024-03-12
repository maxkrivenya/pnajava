package com.example.apihell.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name="id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "spec")
    private String spec;


    @Column(name = "group")
    private String group;

    @Column(name="semester_number")
    private int semesterNumber;

    public int getSemesterNumber() {
        return semesterNumber;
    }

    public void setSemesterNumber(int semester) {
        this.semesterNumber = semester;
    }

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student", cascade = CascadeType.REMOVE)
    List<Mark> marks;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student", cascade = CascadeType.REMOVE)
    List<Skip> skips;

    @JsonBackReference
    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    List<Professor> professors;

    public List<Skip> getSkips() {
        return skips;
    }

    public void setSkips(List<Skip> skips) {
        this.skips = skips;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public Student(String id, String name, String faculty, String spec, String group, int semesterNumber) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.spec = spec;
        this.group = group;
        this.semesterNumber = semesterNumber;
    }

    public Student() {
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", faculty=" + faculty + ", spec=" + spec + ", group=" + group + "]";
    }

}
