package model.modelDAO.studentDAO;

import model.entities.Student;
import model.modelDAO.AbstractDAO;

public class StudentDAOImpl extends AbstractDAO<Student> implements StudentDAO {
    public StudentDAOImpl(){
        super(Student.class);
    }
}
