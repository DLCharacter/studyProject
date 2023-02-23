package model.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="groups")
public class Group {
    //class fields
    @Id
    @Column(name="groupid")
    private int groupId;
    @Column(name="groupnum")
    private int groupNum;
    @ManyToOne
    @JoinColumn(name="faculty")
    private Faculty faculty;
    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();
    //constructor
    public Group(){
    }
    //getters
    public int getGroupId() {
        return groupId;
    }
    public int getGroupNum() {
        return groupNum;
    }
    public Faculty getFaculty() {
        return faculty;
    }
    public Set getStudents() {
        return students;
    }
    //setters
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    public void setStudents(Set students) {
        this.students = students;
    }
}
