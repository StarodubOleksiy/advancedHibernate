package ua.goit.java.hibernate.model;

import java.util.List;

/**
 * Created by Администратор on 09.06.16.
 */
public interface DishDao {
    void save(Dish dish);
    void remove(Long id);
     List<Dish> findAll();
    Dish findById(Long id);
    Dish findByName(String name);

}
