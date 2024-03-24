package com.example.apihell.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "professors")
public class Professor {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="title")
    private String title;

    @Column(name="department")
    private String department;
/*
    @OneToMany(fetch = FetchType.LAZY)
    List<Mark> marks;
*/
    public Professor(){}
    public Professor(String id, String title, String department) {
        this.id = id;
        this.title = title;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
