package com.example.apihell.model;
import com.example.apihell.base.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
//@JsonIgnoreProperties({"marks","group","skips"})
public class Student extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name="surname")
    private String surname;
    @Column(name="name")
    private String name;
    @Column(name="patronim", nullable = true)
    private String patronim;

    @OneToMany(mappedBy ="student", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    private List<Mark> marks;

    @OneToMany(mappedBy ="student", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    private List<Skip> skips;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "group-id")
    @JsonManagedReference
    private Group group;

    public Student(String id, String surname, String name, String patronim) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronim = patronim;
    }

    public Student(String id, String surname, String name, String patronim, List<Mark> marks, List<Skip> skips, Group group) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronim = patronim;
        this.marks = marks;
        this.skips = skips;
        this.group = group;
    }
    public Student(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronim() {
        return patronim;
    }

    public void setPatronim(String patronim) {
        this.patronim = patronim;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}