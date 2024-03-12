package com.example.apihell.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "group")
public class Group {
    @Id
    private String id;
    @Column(name="degree")
    private String degree;
    private String faculty;

    public Group(String id, String degree, String faculty) {
        this.id = id;
        this.degree = degree;
        this.faculty = faculty;
    }

    public Group(){
    }

    public String getId() {
        return id;
    }

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
}
