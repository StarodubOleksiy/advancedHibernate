package ua.goit.java.hibernate.model.Controllers;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Администратор on 20.08.16.
 */
public class MenuController {


       private DishDao dishDao;
       private MenuDao menuDao;

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Transactional
    public void createMenu() throws IOException {
        Set<Menu> allMenu = new HashSet<>(menuDao.showAll());
        Menu menu = new Menu();
        System.out.println("Adding new menu to data base");
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter name of new menu:");
        String name =  br.readLine().toLowerCase();
        menu.setName(name);
        List<String> allDishes = new ArrayList<>();
        String dish = new String();
        do {
            System.out.println("Enter the name of dish or exit  if you have  entered all dishes:");
            dish = br.readLine().toLowerCase();
            allDishes.add(dish);
        } while(!dish.equals("exit"));
        menu.setDishes(createDishes(allDishes));
        if (!allMenu.contains(menu)) {
            menuDao.save(menu);
            System.out.println("A new menu was saved to data-base successfully!!!");
        }
    }


    @Transactional
    public void addDishesToMenu() throws  IOException
    {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        List<String> allDishes = new ArrayList<>();
        System.out.println("Enter name of the menu you are going to add new dishes:");
        String name =  br.readLine().toLowerCase();
        Menu menu = menuDao.findByName(name);
       for(int  i = 0; i < menu.getDishes().size(); ++i )
           allDishes.add(menu.getDishes().get(i).getName());

        String dish = new String();
        do {
            System.out.println("Enter the name of dish or exit  if you have  entered all dishes:");
            dish = br.readLine().toLowerCase();
            allDishes.add(dish);
        } while(!dish.equals("exit"));
        menu.setDishes(createDishes(allDishes));
        System.out.println("A new dish was added to menu successfully!");
    }

    @Transactional
    public void removeDishesFromMenu() throws  IOException
    {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
    //    List<String> allDishes = new ArrayList<>();
        System.out.println("Enter name of the menu you are going to remove dishes:");
        String name =  br.readLine().toLowerCase();
        Menu menu = menuDao.findByName(name);
        /*for(int  i = 0; i < menu.getDishes().size(); ++i )
            allDishes.add(menu.getDishes().get(i).getName());*/

        String dish = new String();
        do {
            System.out.println("Enter the name of dish or exit  if you have  entered all dishes:");
            dish = br.readLine().toLowerCase();
         for(int i = 0; i < menu.getDishes().size(); ++i)
                if(menu.getDishes().get(i).getName().equals(dish))
                {
                    menu.getDishes().remove(i);
                }
//         menu.getDishes().remove(dishDao.findByName(name));

        } while(!dish.equals("exit"));
     //  menu.setDishes(createDishes(allDishes));
        System.out.println("A dish was removed from menu successfully!");
    }


    private List<Dish> createDishes(List<String> dishes) {
        List<Dish> result = new ArrayList<>();
        for (String dishName: dishes) {
            result.add(dishDao.findByName(dishName));
        }
        return result;
    }

    @Transactional
    public Menu getMenuById(Long id)
    {
        return menuDao.findById(id);
    }

    @Transactional
    public void deleteMenuById() throws  IOException
    {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter id of  menu you are going to delete:");
        long id = Long.parseLong(br.readLine());
        menuDao.remove(id);
        System.out.println("Menu was deleted successfully!");
    }



    @Transactional
    public Menu getMenuByName() throws  IOException
    {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter name of menu you are going to find:");
        String name = br.readLine();
        return menuDao.findByName(name);
    }


    @Transactional
    public List<Menu> getAllMenus()
    {
        return menuDao.showAll();
    }

}
