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

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name="professor-id", referencedColumnName = "id")
    @JsonManagedReference
    private List<Mark> marks;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name="professor-id", referencedColumnName = "id")
    @JsonManagedReference
    private List<Skip> skips;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name="subject-professor",
            joinColumns = @JoinColumn(name = "professor-id"),
            inverseJoinColumns = @JoinColumn(name = "subject-id"))
    @JsonManagedReference
    private List<Subject> subjects;

    public Professor(){}
    public Professor(String id, String title, String department) {
        this.setId(id);
        this.title = title;
        this.department = department;
    }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public List<Mark> getMarks() { return marks; }
    public void setMarks(List<Mark> marks) { this.marks = marks; }
    public List<Skip> getSkips() { return skips; }
    public void setSkips(List<Skip> skips)  { this.skips = skips; }
    public List<Subject> getSubjects() { return subjects; }
    public void setSubjects(List<Subject> subjects) { this.subjects = subjects; }
}
