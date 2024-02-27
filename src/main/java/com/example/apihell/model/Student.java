package com.example.apihell.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "fio")
    private String name;

    @Column(name = "faculty")
    private String faculty;


    @Column(name = "spec")
    private String spec;


    @Column(name = "group")
    private String group;

    public Student(String id, String name, String faculty, String spec, String group) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.spec = spec;
        this.group = group;

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
