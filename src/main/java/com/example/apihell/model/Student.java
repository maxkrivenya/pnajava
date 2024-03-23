package com.example.apihell.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name="id")
    private String id;
    @Column(name="group-id")
    private String groupId;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "student")
    private PersonalData personalData;
    @OneToMany(fetch = FetchType.EAGER)
    List<Mark> marks;
    @OneToMany(fetch = FetchType.EAGER)
    List<Skip> skips;

    public Student(){}

    public Student(String id, String groupId, Integer semesterNumber, PersonalData personalData, List<Mark> marks, List<Skip> skips) {
        this.id = id;
        this.groupId = groupId;
        this.personalData = personalData;
        this.marks = marks;
        this.skips = skips;
    }

    public Student(String id, String groupId, Integer semesterNumber) {
        this.id = id;
        this.groupId = groupId;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
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
}