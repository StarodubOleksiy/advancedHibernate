package ua.goit.java.hibernate.model.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.goit.java.hibernate.model.Dish;
import ua.goit.java.hibernate.model.DishDao;
import ua.goit.java.hibernate.model.Employee;

import java.util.List;

/**
 * Created by Администратор on 09.06.16.
 */
public class HDishDao implements DishDao {
    @Override
    public Dish findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from Dish d where d.name like:name");
        query.setParameter("name",name);
        return (Dish)query.uniqueResult();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private SessionFactory sessionFactory;

    @Override
    public Dish findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return  (Dish) session.get(Dish.class, id);
      }

    @Override
    public void save(Dish dish) {
        sessionFactory.getCurrentSession().save(dish);
    }

    @Override
    public List<Dish> findAll() {
        return sessionFactory.getCurrentSession().createQuery("select d from Dish d").list();
    }

    @Override
    public void remove(Long id) {

        sessionFactory.getCurrentSession().delete(findById(id));
    }
}
