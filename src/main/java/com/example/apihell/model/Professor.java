package com.example.apihell.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "profs")
public class Professor {

    @Id
    @Column(name="name")
    private String name;

    @Column(name="dolzhnost")
    private String title;

    @Column(name="kafedra")
    private String department;

    public Professor(String name, String title, String department) {
        this.name = name;
        this.title = title;
        this.department = department;
    }

    public Professor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
