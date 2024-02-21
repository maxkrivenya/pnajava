package com.example.apihell.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "fac")
    private String fac;


    @Column(name = "spec")
    private String spec;


    @Column(name = "group")
    private int group;

    public Student(String id, String name, String fac, String spec, int group) {
        this.id = id;
        this.name = name;
        this.fac = fac;
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

    public String getFac() {
        return fac;
    }

    public void setFac(String fac) {
        this.fac = fac;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", name=" + name + ", fac=" + fac + ", spec=" + spec + ", group=" + group + "]";
    }

}
