package ua.goit.java.hibernate.model.hibernate;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.model.Dish;
import ua.goit.java.hibernate.model.Employee;
import ua.goit.java.hibernate.model.EmployeeDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.goit.java.hibernate.model.Waiter;

import java.util.List;

public class HEmployeeDao implements EmployeeDao {
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void save(Employee employee) {
        sessionFactory.getCurrentSession().save(employee);
    }

    @Override
    @Transactional
    public Employee load(Long id) {
        Employee result = sessionFactory.getCurrentSession().load(Employee.class, id);
        if (result == null) {
            throw new RuntimeException("Cannot find Employee with ID:" + id);
        }
        return result;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(" select e from Employee e").list();
    }

    @Override
    @Transactional
    public Employee findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Employee e where e.name like :name");
        query.setParameter("name",name);
        return (Employee)query.uniqueResult();
    }


    @Override
    @Transactional
    public Waiter findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return  (Waiter) session.get(Waiter.class, id);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void remove(Long id) {

        sessionFactory.getCurrentSession().delete(findById(id));
    }
}
