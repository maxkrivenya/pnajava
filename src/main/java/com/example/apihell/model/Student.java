package com.example.apihell.model;
import com.example.apihell.model.base.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Student extends Person {

    @Column(name="group-id")
    private String groupId;

    @OneToMany(mappedBy ="student", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Mark> marks;

    @OneToMany(mappedBy ="student", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Skip> skips;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "group-id", insertable=false, updatable=false)
    private Group group;

    public Student(){}

    public Student(String id, String surname, String name, String patronim) {
        super(id, surname, name, patronim);
    }

    public Student(String id, String surname, String name, String patronim, String groupId) {
        super(id, surname, name, patronim);
        this.groupId = groupId;
    }

    public Student(String id, String surname, String name, String patronim, String groupId, List<Mark> marks, List<Skip> skips, Group group) {
        super(id,surname,name,patronim);
        this.marks = marks;
        this.skips = skips;
        this.group = group;
        this.groupId = groupId;
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void fill(Student student){
        if(student==null){
            return;
        }
        if(student.getName() != null && !Objects.equals(this.getName(), student.getName())){
            this.setName(student.getName());
        }
        if(student.getSurname() != null && !Objects.equals(this.getSurname(), student.getSurname())){
            this.setSurname(student.getSurname());
        }
        if(student.getPatronim() != null && !Objects.equals(this.getPatronim(), student.getPatronim())){
            this.setPatronim(student.getPatronim());
        }
        if(student.getGroupId() != null && !Objects.equals(this.getGroupId(), student.getGroupId())){
            this.setGroupId(student.getGroupId());
        }
    }
}


