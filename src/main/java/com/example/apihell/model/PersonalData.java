package com.example.apihell.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="personal-data")
public class PersonalData {
    @Id
    @Column(name="id")
    private String id;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="patronim")
    private String patronim;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private Student student;

    public PersonalData(){}
    public PersonalData(String id, String name, String surname, String patronim) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronim = patronim;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronim() {
        return patronim;
    }

    public void setPatronim(String patronim) {
        this.patronim = patronim;
    }
}
