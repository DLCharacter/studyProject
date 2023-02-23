package model.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="faculties")
public class Faculty {
    //class fields
    @Id
    @Column(name="facultyid")
    private int facultyId;
    @Column(name="facultyname")
    private String facultyName;
    @Column(name="facultydescription")
    private String facultyDescription;
    @OneToMany(mappedBy = "faculty",cascade = CascadeType.ALL)
    private Set<Group> groups = new HashSet<>();
    //constructor
    public Faculty(){
    }
    //getters
    public int getFacultyId() {
        return facultyId;
    }
    public String getFacultyName() {
        return facultyName;
    }
    public String getFacultyDescription() {
        return facultyDescription;
    }
    public Set getGroups() {
        return groups;
    }
    //setters
    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
    public void setFacultyDescription(String facultyDescription) {
        this.facultyDescription = facultyDescription;
    }
    public void setGroups(Set groups) {
        this.groups = groups;
    }
}
