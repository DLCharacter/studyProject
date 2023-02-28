package model.modelDAO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.util.HibernateSessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

//This is the class that implements basic actions with entities
//Now is`s: inserting, deleting and updating item;
//selecting item by id and selecting all items;
//getting count of items.
public abstract class AbstractDAO<T> {
    private final Class<T> parameterClass;
    public AbstractDAO(Class<T> parameterClass){
        this.parameterClass = parameterClass;
    }
    public void addItem(T item){
        Session session = HibernateSessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(item);
        transaction.commit();
        session.close();
    }
    public void updateItem(T item){
        Session session = HibernateSessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(item);
        transaction.commit();
        session.close();
    }
    public void deleteItem(T item)
    {
        Session session = HibernateSessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(item);
        transaction.commit();
        session.close();
    }
    public void deleteItemById(int id){
        Session session = HibernateSessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        T item = getItemById(id);
        session.remove(item);
        transaction.commit();
        session.close();
    }

    //implements in entities dao classes
    public T getItemById(int id){
        Session session = HibernateSessionUtil.getSession();
        T item = session.get(parameterClass,id);
        session.close();
        return item;
    }
    public List<T> getAllItems(){
        List<T> items;
        Session session = HibernateSessionUtil.getSession();
        //creating criteria query
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(parameterClass);
        //creating root
        Root<T> queryRoot = criteriaQuery.from(parameterClass);
        //configuring criteria
        criteriaQuery.select(queryRoot);
        //creating and executing query
        Query<T> query = session.createQuery(criteriaQuery);
        items = query.getResultList();
        session.close();
        return items;
    }
    public Long getItemsCount(){
        Long itemsCount;
        Session session = HibernateSessionUtil.getSession();
        //creating criteria query
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        //creating root
        Root<T> queryRoot = criteriaQuery.from(parameterClass);
        //configuring criteria
        criteriaQuery.select(builder.count(queryRoot));
        //creating and executing query
        Query<Long> query = session.createQuery(criteriaQuery);
        itemsCount = query.getSingleResult();
        session.close();
        return itemsCount;
    }
}
