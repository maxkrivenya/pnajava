package com.example.apihell.model;
import com.example.apihell.base.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
@JsonIgnoreProperties({"marks","group","skips"})
public class Student extends Person {

    @OneToMany(mappedBy ="student",cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private List<Mark> marks;

    @OneToMany(mappedBy ="student",cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private List<Skip> skips;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "group-id")
    @JsonManagedReference
    private Group group;

    public Student(){ super(); }

    public Student(String id, String surname, String name, String patronim, List<Mark> marks) {
        super(id, surname, name, patronim);
        this.marks = marks;
    }

    public Student(Person person) {
        super(person);
    }

    public Student(String id, String surname, String name, String patronim) {
        super(id, surname, name, patronim);
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

     public String toString(){
        return this.getSurname() + this.getName() + this.getPatronim();
    }

}