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
 * Created by Администратор on 11.06.16.
 */
public class OrderController {

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    private OrderDao orderDao;
    private EmployeeDao employeeDao;
    private DishDao dishDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Transactional
    public void createOrder() throws IOException
    {
        Orders order = new Orders();
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter id of employee who have to make this  order:");
        long waiterId = Long.parseLong(br.readLine());
        order.setWaiter(employeeDao.findById(waiterId));
        List<String> dishes = new ArrayList<>();
        String dish = new String();
        do {
        System.out.println("Enter the name of dish or exit  if you have  entered all dishes:");
        dish = br.readLine().toLowerCase();
        dishes.add(dish);
        } while(!dish.equals("exit"));
        order.setDishes(createDishes(dishes));
        System.out.println("Enter number of the table to this  order:");
        int tableNumber = Integer.parseInt(br.readLine());
        order.setTableNumber(tableNumber);
        order.setOrderDate(new Date().toString());
        order.setState("open");
       orderDao.save(order);
        System.out.println("A new order was created successfully!!!:");
    }

    @Transactional
    public List<Orders> getAllOrders() {
        return orderDao.findAllOrders();
    }


    @Transactional
    public List<Orders> getCloseOrders() {
        return orderDao.findCloseOrders();
    }

    @Transactional
    public void printAllOrders() {
       getAllOrders().forEach(System.out::println);
    }

    @Transactional
    public void printCloseOrders() {
        getCloseOrders().forEach(System.out::println);
    }

//
    @Transactional
    public void setClose() throws  IOException{
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter id of order you are going to set close:");
        long id = Long.parseLong(br.readLine());
       orderDao.setClose(id);
        System.out.println("A order was setted close successfully!!!");
    }



    private List<Dish> createDishes(List<String> dishes) {
        List<Dish> result = new ArrayList<>();
        for (String dishName: dishes) {
            result.add(dishDao.findByName(dishName));
        }
        return result;
    }


    @Transactional
    public void addNewDishes() throws  IOException{
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter id of order you want to add new dishes:");
        long id = Long.parseLong(br.readLine());
        Orders order = orderDao.findById(id);
        if(order.getState().equals("close"))
            throw new IOException("Add dishes to close order is forbidden");
        String dishName = new String();
        do {
            System.out.println("Enter the name of dish or exit  if you have  entered all dishes:");
            dishName = br.readLine().toLowerCase();
            order.getDishes().add(dishDao.findByName(dishName));
            orderDao.updateDish(order);
            System.out.println("A new dish was added successfully!!!");
            } while(!dishName.equals("exit"));
      }


    @Transactional
    public void deleteDishes() throws  IOException{
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter id of order you want to remove dish:");
        long id = Long.parseLong(br.readLine());
        Orders order = orderDao.findById(id);
        if(order.getState().equals("close"))
            throw new IOException("Delete dishes from close order is forbidden");
        String dishName = new String();
        do {
            System.out.println("Enter the name of dish or exit  if you have  entered all dishes:");
            dishName = br.readLine().toLowerCase();
            order.getDishes().remove(dishDao.findByName(dishName));
            orderDao.updateDish(order);
            System.out.println("A dish was removed successfully!!!");
        } while(!dishName.equals("exit"));
    }


    @Transactional
    public void deleteOrder() throws  IOException
    {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter id of  order you are going to delete:");
        long id = Long.parseLong(br.readLine());
         orderDao.remove(id);
        System.out.println("Order was deleted successfully!");
    }





}
