package com.example.apihell.model;
import com.example.apihell.model.base.Person;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student extends Person {
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

    public Student(){}
    public Student(String id, String surname, String name, String patronim) {
        super(id, surname, name, patronim);
    }
    public Student(String id, String surname, String name, String patronim, List<Mark> marks, List<Skip> skips, Group group) {
        super(id,surname,name,patronim);
        this.marks = marks;
        this.skips = skips;
        this.group = group;
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
    public void setSkips(List<Skip> skips) { this.skips = skips; }
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
}