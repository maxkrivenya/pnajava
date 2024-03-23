package com.example.apihell.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="subjects")
public class Subject {
    @Id
    @Column(name = "id")
    String id;
    @Column(name = "name")
    String name;
    @Column(name = "full-name")
    String fullName;
    @Column(name = "semester-id")
    String semesterId;

    @OneToMany(fetch = FetchType.LAZY)
    List<Mark> marks;

    public Subject(){}
    public Subject(String id, String name, String fullName, String semesterId) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.semesterId = semesterId;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }
}