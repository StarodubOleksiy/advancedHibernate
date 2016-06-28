package ua.goit.java.hibernate.model;

import java.util.List;

/**
 * Created by Администратор on 20.06.16.
 */
public interface StorageDao {
    void save(Storage storage);
    List<Storage> findAll();
    void remove(Long id);
    public Storage findByName(String name);
    public Storage findById(Long id);
    List<Storage> printEnded();
    void changeNumerosity(Long id, int numerosity);


}
