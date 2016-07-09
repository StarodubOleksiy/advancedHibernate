package ua.goit.java.hibernate.model.hibernate;

import org.hibernate.SessionFactory;
import ua.goit.java.hibernate.model.CookedDishDao;
import ua.goit.java.hibernate.model.Cooked_Dish;

import java.util.List;

/**
 * Created by Администратор on 07.07.16.
 */
public class HCookedDishDao implements CookedDishDao {

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private SessionFactory sessionFactory;

    @Override
    public void save(Cooked_Dish cookedDish) {
        sessionFactory.getCurrentSession().save(cookedDish);
    }

    @Override
    public List<Cooked_Dish> seeAll() {
        return sessionFactory.getCurrentSession().createQuery("select d from Cooked_Dish d").list();
    }
}
