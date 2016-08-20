package ua.goit.java.hibernate.model.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.goit.java.hibernate.model.Dish;
import ua.goit.java.hibernate.model.EmployeeDao;
import ua.goit.java.hibernate.model.Menu;
import ua.goit.java.hibernate.model.MenuDao;

import java.util.List;

/**
 * Created by Администратор on 20.08.16.
 */
public class HMenuDao implements MenuDao {

    @Override
    public Menu findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from Menu d where d.name like:name");
        query.setParameter("name",name);
        return (Menu)query.uniqueResult();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private SessionFactory sessionFactory;

    @Override
    public Menu findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Menu) session.get(Menu.class, id);
    }

    @Override
    public void save(Menu menu) {
        sessionFactory.getCurrentSession().save(menu);
    }

    @Override
    public List<Menu> showAll() {
        return sessionFactory.getCurrentSession().createQuery("select d from Menu d").list();
    }

    @Override
    public void remove(Long id) {
        Menu menu = findById(id);
        menu.setDishes(null);
        sessionFactory.getCurrentSession().delete(menu);
    }

}
