package com.example.apihell.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

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

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "sem",
            joinColumns = @JoinColumn(name = "lecturer"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Professor(String name, String title, String department) {
        this.name = name;
        this.title = title;
        this.department = department;
    }

    public Professor() {
    }

    public Professor(String name, String title, String department, List<Student> students) {
        this.name = name;
        this.title = title;
        this.department = department;
        this.students = students;
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
