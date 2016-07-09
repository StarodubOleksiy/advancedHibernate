package ua.goit.java.hibernate.model;

import java.util.List;

/**
 * Created by Администратор on 07.07.16.
 */
public interface CookedDishDao {
    public void save(Cooked_Dish cookedDish);
     public List<Cooked_Dish> seeAll();
}
