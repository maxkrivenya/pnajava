package com.example.apihell.model;
import com.example.apihell.base.Person;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student extends Person {

    @Column(name="group-id")
    private String groupId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="student-id", referencedColumnName = "id")
    @JsonManagedReference
    private List<Mark> marks;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="student-id", referencedColumnName = "id")
    @JsonManagedReference
    private List<Skip> skips;

    public Student(){ super(); }
    public Student(String id, String surname, String name, String patronim, String groupId) {
        super(id, surname, name, patronim);
        this.groupId = groupId;
    }
    public Student(String id, String surname, String name, String patronim, String groupId, List<Mark> marks, List<Skip> skips) {
        super(id, surname, name, patronim);
        this.groupId = groupId;
        this.marks = marks;
        this.skips = skips;
    }

    public String getGroupId() { return groupId; }
    public void setGroupId(String groupId) { this.groupId = groupId; }
    public List<Mark> getMarks() { return marks; }
    public void setMarks(List<Mark> marks) { this.marks = marks; }
    public List<Skip> getSkips() { return skips; }
    public void setSkips(List<Skip> skips) { this.skips = skips; }

}