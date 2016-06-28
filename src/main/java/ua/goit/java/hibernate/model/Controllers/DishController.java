package ua.goit.java.hibernate.model.Controllers;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.model.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Администратор on 09.06.16.
 */
public class DishController {

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    private DishDao dishDao;

    @Transactional
    public void createDish() throws IOException {
        Set<Dish> allDishes = new HashSet<>(dishDao.findAll());
        Dish dish = new Dish();
        System.out.println("Adding new dish to data base");
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter name of new dish:");
        String name =  br.readLine().toLowerCase();
        dish.setName(name);
        System.out.println("Enter category of new dish:");
        String position =  br.readLine().toLowerCase();
        if(position.equals("first"))
           dish.setDishCategory(DishCategory.FIRSTDISH);
        else if(position.equals("second"))
            dish.setDishCategory(DishCategory.SECONDDISH);
        else if(position.equals("third"))
            dish.setDishCategory(DishCategory.THIRDDISH);
        else throw new IOException("You enter invalid category");
        System.out.println("Enter price of new dish:");
        float price =  Float.parseFloat(br.readLine());
        dish.setPrice(price);
        System.out.println("Enter weight of new dish:");
        float weight =  Float.parseFloat(br.readLine());
        dish.setWeight(weight);
        if (!allDishes.contains(dish)) {
            dishDao.save(dish);
            System.out.println("A new dish was saved to data-base successfully!!!");
        }
    }

    @Transactional
    public Dish getDishById(Long id)
    {
        return dishDao.findById(id);
    }

    @Transactional
    public void deleteDishById() throws  IOException
    {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter id of  dish you are going to delete:");
        long id = Long.parseLong(br.readLine());
        dishDao.remove(id);
        System.out.println("Dish was deleted successfully!");
    }

    @Transactional
    public Dish getDishByName() throws  IOException
    {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter name of ingradient you are going to find:");
        String name = br.readLine();
        return dishDao.findByName(name);
    }


    @Transactional
    public List<Dish> getAllDishes()
    {
        return dishDao.findAll();
    }
}

