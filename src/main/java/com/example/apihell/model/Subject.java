package com.example.apihell.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="subjects")
@JsonIgnoreProperties({"marks","skips"})
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    String id;
    @Column(name = "name")
    String name;
    @Column(name = "full-name")
    String fullName;
    @Column(name = "semester-id")
    String semesterId;

    @OneToMany(mappedBy ="subject",cascade = CascadeType.MERGE, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private List<Mark> marks;

    @OneToMany(mappedBy ="subject",cascade = CascadeType.MERGE, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private List<Skip> skips;
    public Subject(){}
    public Subject(String id, String name, String fullName, String semesterId) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.semesterId = semesterId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getSemesterId() { return semesterId; }
    public void setSemesterId(String semesterId) { this.semesterId = semesterId; }
    public List<Mark> getMarks() { return marks; }
    public void setMarks(List<Mark> marks) { this.marks = marks; }
    public List<Skip> getSkips() { return skips; }
    public void setSkips(List<Skip> skips) { this.skips = skips; }
}