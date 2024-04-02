package com.example.apihell.model.base;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public abstract class LectureResult implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private String id;
    @Column(name="date", nullable = false)
    private String date;
    @Column(name="value", nullable = false)
    private Integer value;

    protected LectureResult(){}

    protected LectureResult(String id, String date, Integer value) {
        this.id = id;
        this.date = date;
        this.value = value;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public Integer getValue() { return value; }
    public void setValue(Integer value) { this.value = value; }
}
