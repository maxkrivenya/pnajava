package com.example.apihell.model;

import com.example.apihell.model.base.Person;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "professors")
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

    public Professor(){
        super();
    }

    public Professor(String string){
        String[] params = string.split(",");
        if(params.length < 5){return;}
        this.setId(params[0]);
        this.title = params[1];
        this.department = params[2];
        this.setSurname(params[3]);
        this.setName(params[4]);
        this.setPatronim(params[5]);
        this.setMarks(new ArrayList<>());
        this.setSkips(new ArrayList<>());
        this.setSubjects(new ArrayList<>());
    }

    public Professor(String id, String surname, String name, String patronim, String title, String department) {
        super(id, surname, name, patronim);
        this.title = title;
        this.department = department;
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
