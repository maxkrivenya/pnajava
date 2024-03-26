package com.example.apihell.model;

import com.example.apihell.base.LectureResult;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="skips")
//@JsonIgnoreProperties({"student","professor","subject"})
public class Skip {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private String id;
    @Column(name="date", nullable = false)
    private String date;
    @Column(name="value", nullable = false)
    private Integer value;

    public Skip() {

    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public Integer getValue() { return value; }
    public void setValue(Integer value) { this.value = value; }
    @Column(name="reasonable")
    private Boolean reasonable;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student-id", referencedColumnName = "id")
    @JsonBackReference
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "professor-id", referencedColumnName = "id")
    @JsonBackReference
    private Professor professor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject-id", referencedColumnName = "id")
    @JsonBackReference
    private Subject subject;

    public Skip(String id, String date, Integer value, Boolean reasonable) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.reasonable = reasonable;
    }

    public Boolean getReasonable() {
        return reasonable;
    }
    public void setReasonable(Boolean reasonable) {
        this.reasonable = reasonable;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public Professor getProfessor() { return professor;}
    public void setProfessor(Professor professor) { this.professor = professor; }
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }


}
