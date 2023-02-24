package model.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HibernateSessionUtil {
    private static SessionFactory sessionFactory;
    private static Properties hibernateProperties;
    private static SessionFactory getSessionFactory()
    {
        //Building session factory if in not built yet
        if(sessionFactory == null)
        {
            //Configuring Hibernate by properties file
            try {
                setDefaultProperties();
            }
            catch (IOException e) {
                String message = "Не найден .properties файл с настройками Hibernate "+e.getMessage();
                String title = "Ошибка";
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
            }
            //Building session factory
            try {
                sessionFactory = new Configuration()
                        .addProperties(hibernateProperties)
                        .addAnnotatedClass(model.entities.Faculty.class)
                        .addAnnotatedClass(model.entities.Group.class)
                        .addAnnotatedClass(model.entities.Student.class)
                        .buildSessionFactory();
            }
            catch (HibernateException e){
                String message = "Ошибка при конфигурации Hibernate "+e.getMessage();
                String title = "Ошибка";
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
            }
        }
        return sessionFactory;
    }
    //Reading default settings from hibernate.properties file
    private static void setDefaultProperties() throws IOException {
        if(hibernateProperties == null) {
            hibernateProperties = new Properties();
            hibernateProperties.load(new FileInputStream("src/main/resources/hibernate.properties"));
        }
    }
    public static Session getSession(){
        return getSessionFactory().openSession();
    }
}
