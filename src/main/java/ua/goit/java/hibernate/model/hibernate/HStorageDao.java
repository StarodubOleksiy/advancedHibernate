package ua.goit.java.hibernate.model.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.goit.java.hibernate.model.Dish;
import ua.goit.java.hibernate.model.Orders;
import ua.goit.java.hibernate.model.Storage;
import ua.goit.java.hibernate.model.StorageDao;

import java.util.List;

/**
 * Created by Администратор on 20.06.16.
 */
public class HStorageDao implements StorageDao {

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private SessionFactory sessionFactory;

    @Override
    public void save(Storage storage) {
        sessionFactory.getCurrentSession().save(storage);
    }

    @Override
    public List<Storage> findAll() {
        return sessionFactory.getCurrentSession().createQuery("select d from Storage d").list();
    }

    @Override
    public List<Storage> printEnded() {
        return sessionFactory.getCurrentSession().createQuery("select d from Storage d  where numerosity < 10").list();
    }

    @Override
    public Storage findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from Storage d where d.name like:name");
        query.setParameter("name",name);
        return (Storage)query.uniqueResult();
    }


    @Override
    public Storage findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return  (Storage) session.get(Storage.class, id);
    }


    @Override
    public void remove(Long id) {

        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    public void changeNumerosity(Long id, int numerosity) {
        Session session = sessionFactory.getCurrentSession();
        Storage storage = session.get(Storage.class, id);
        storage.setNumerosity(numerosity);
        sessionFactory.getCurrentSession().update(storage);
     }





}
