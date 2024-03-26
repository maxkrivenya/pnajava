package com.example.apihell.model;

import com.example.apihell.base.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "professors")
//@JsonIgnoreProperties({"marks","skips", "subjects"})
public class Professor extends Person {
    @Column(name="title")
    private String title;

    @Column(name="department")
    private String department;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name="professor-id", referencedColumnName = "id")
    @JsonManagedReference
    private List<Mark> marks;

    @OneToMany(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name="professor-id", referencedColumnName = "id")
    @JsonManagedReference
    private List<Skip> skips;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(
            name="subject-professor",
            joinColumns = @JoinColumn(name = "professor-id"),
            inverseJoinColumns = @JoinColumn(name = "subject-id"))
    @JsonManagedReference
    private List<Subject> subjects;

    public Professor(){}
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name="surname")
    private String surname;
    @Column(name="name")
    private String name;
    @Column(name="patronim", nullable = true)
    private String patronim;

    public Professor(String title, String department, List<Mark> marks, List<Skip> skips, List<Subject> subjects, String id, String surname, String name, String patronim) {
        this.title = title;
        this.department = department;
        this.marks = marks;
        this.skips = skips;
        this.subjects = subjects;
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronim = patronim;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPatronim() {
        return patronim;
    }
    public void setPatronim(String patronim) {
        this.patronim = patronim;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public List<Skip> getSkips() {
        return skips;
    }

    public void setSkips(List<Skip> skips) {
        this.skips = skips;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
