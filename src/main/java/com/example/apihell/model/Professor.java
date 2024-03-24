package com.example.apihell.model;

import com.example.apihell.base.Person;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "professors")
public class Professor extends Person {
    @Column(name="title")
    private String title;

    @Column(name="department")
    private String department;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="professor-id", referencedColumnName = "id")
    @JsonManagedReference
    private List<Mark> marks;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="professor-id", referencedColumnName = "id")
    @JsonManagedReference
    private List<Skip> skips;

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
}
