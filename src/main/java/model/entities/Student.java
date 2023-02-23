package model.entities;

import jakarta.persistence.*;

@Entity
@Table(name="students")
public class Student {
    //class fields
    @Id
    @Column(name="studentid")
    private int studentId;
    @Column(name="studentfio")
    private String studentFIO;
    @ManyToOne
    @JoinColumn(name="studentgroup")
    private Group group;
    //constructor
    public Student(){
    }
    //getters
    public int getStudentId() {
        return studentId;
    }
    public String getStudentFIO() {
        return studentFIO;
    }
    public Group getGroup() {
        return group;
    }
    //setters
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public void setStudentFIO(String studentFIO) {
        this.studentFIO = studentFIO;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
}
