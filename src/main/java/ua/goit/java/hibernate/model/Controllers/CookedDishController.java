package ua.goit.java.hibernate.model.Controllers;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Администратор on 07.07.16.
 */
public class CookedDishController {
    private DishDao dishDao;
    private OrderDao orderDao;
    private EmployeeDao employeeDao;
    private CookedDishDao cookedDishDao;



    private StorageDao storageDao;


    public void setCookedDishDao(CookedDishDao cookedDishDao) {
        this.cookedDishDao = cookedDishDao;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setStorageDao(StorageDao storageDao) { this.storageDao = storageDao;     }


    @Transactional
    public List<Cooked_Dish> getAllCookedDishes()
    {
        return cookedDishDao.seeAll();
    }

   @Transactional
    public void createCookedDish() throws IOException
    {
        Cooked_Dish cooked_dish = new Cooked_Dish();
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter the name of dish");
        String dishName = br.readLine().toLowerCase();
        Dish dish =dishDao.findByName(dishName);

        for (Storage ingradients: dish.getIngradients()) {
            storageDao.decreaseNumerosity(ingradients,10); //Логика, при которой при создании нового приготовленного блюда на складе уменьшается соответствующее количество ингредиентов.
        }
        cooked_dish.setDish(dish);
        System.out.println("Enter id of employee who have to make this  order:");
        long cookId = Long.parseLong(br.readLine());
        cooked_dish.setCook((Cook) employeeDao.findById(cookId));

        System.out.println("Enter number of order:");
        long orderId = Integer.parseInt(br.readLine());
        cooked_dish.setOrder(orderDao.findById(orderId));
        cooked_dish.setOrderDate(new Date().toString());
        cookedDishDao.save(cooked_dish);
        System.out.println("A new dish was cooked successfully!!!:");
    }

    @Transactional
    public void createCookedDish(String dishName, Orders order) throws IOException
    {
        Cooked_Dish cooked_dish = new Cooked_Dish();
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
         Dish dish =dishDao.findByName(dishName);
        for (Storage ingradients: dish.getIngradients()) {
            storageDao.decreaseNumerosity(ingradients,10); //Логика, при которой при создании нового приготовленного блюда на складе уменьшается соответствующее количество ингредиентов.
        }
        cooked_dish.setDish(dish);
        System.out.println("Enter id of employee who have to make this  order:");
        long cookId = Long.parseLong(br.readLine());
        cooked_dish.setCook((Cook) employeeDao.findById(cookId));

        System.out.println("Enter number of order:");
         cooked_dish.setOrder(order);
        cooked_dish.setOrderDate(new Date().toString());
        cookedDishDao.save(cooked_dish);
        System.out.println("A new dish was cooked successfully!!!:");
    }



}
