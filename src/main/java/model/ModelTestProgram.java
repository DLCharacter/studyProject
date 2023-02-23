package model;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ModelTestProgram
{
    public static SessionFactory sessionFactory;
    public static void main(String[] args)
    {
        try {
            Properties hProperties = new Properties();
            hProperties.load(new FileInputStream("src/main/resources/hibernate.properties"));
            sessionFactory = new Configuration()
                    .addProperties(hProperties)
                    .addAnnotatedClass(model.entities.Faculty.class)
                    .addAnnotatedClass(model.entities.Group.class)
                    .addAnnotatedClass(model.entities.Student.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
        }
        try {
            Session session = sessionFactory.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Student> cq = cb.createQuery(Student.class);
            Root<Student> rootEntry = cq.from(Student.class);
            CriteriaQuery<Student> all = cq.select(rootEntry);

            TypedQuery<Student> allQuery = session.createQuery(all);
            List<Student> arr = new ArrayList<>(allQuery.getResultList());
            for(int i = 0; i<arr.size(); i++)
            {
                System.out.println("id студента: "+arr.get(i).getStudentId()+" ФИО: "+arr.get(i).getStudentFIO());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при выборке", JOptionPane.OK_OPTION);
        }
    }
}
