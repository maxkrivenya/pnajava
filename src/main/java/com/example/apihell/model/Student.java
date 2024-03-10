package com.example.apihell.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "fac")
    private String faculty;


    @Column(name = "spec")
    private String spec;


    @Column(name = "group")
    private String group;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mark> marks;

    public Student(String id, String name, String faculty, String spec, String group) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.spec = spec;
        this.group = group;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
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
