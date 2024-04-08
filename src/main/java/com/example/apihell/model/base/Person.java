package com.example.apihell.model.base;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public abstract class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name="surname")
    private String surname;
    @Column(name="name")
    private String name;
    @Column(name="patronim", nullable = true)
    private String patronim;

    protected Person(){}

    protected Person(String id, String surname, String name, String patronim) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronim = patronim;
    }

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
}