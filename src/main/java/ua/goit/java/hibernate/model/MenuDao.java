package ua.goit.java.hibernate.model;

import java.util.List;

/**
 * Created by Администратор on 20.08.16.
 */
public interface MenuDao {
    void save(Menu menu);
    void remove(Long id);
    List<Menu> showAll();
    Menu findById(Long id);
    Menu findByName(String name);
}
