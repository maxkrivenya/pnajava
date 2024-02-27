package com.example.apihell.model;

import jakarta.persistence.*;
@Table(name = "marks")
@Entity
public class Mark {
    @Column(name="id")
    private String studId;
    @Column(name="mark")
    private Integer value;
    @Column(name="subj")
    private String subj;

    @Id
    @Column(name="date")
    private String date;
    @Column(name="type")
    private String type;

    public String getStudId() {
        return studId;
    }

    public void setStudId(String studId) {
        this.studId = studId;
    }

    public String getSubj() {
        return subj;
    }

    public void setSubj(String subj) {
        this.subj = subj;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }


}
