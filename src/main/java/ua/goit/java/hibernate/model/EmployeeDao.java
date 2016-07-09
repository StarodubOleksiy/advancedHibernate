package ua.goit.java.hibernate.model;

import java.util.List;
public interface EmployeeDao {
    void save(Employee employee);
    Employee load(Long id);
    List<Employee> findAll();
    List<Employee> findAllWaiters();
    List<Employee> findAllCooks();
    void remove(Long id);
    public Employee findByName(String name);
     public Employee findById(Long id);
}
