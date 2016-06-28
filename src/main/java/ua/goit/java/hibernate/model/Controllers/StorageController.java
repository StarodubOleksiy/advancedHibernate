package ua.goit.java.hibernate.model.Controllers;

import org.springframework.transaction.annotation.Transactional;

import ua.goit.java.hibernate.model.Dish;
import ua.goit.java.hibernate.model.Storage;
import ua.goit.java.hibernate.model.StorageDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Администратор on 20.06.16.
 */
public class StorageController {


    public void setStorageDao(StorageDao storageDao) {
        this.storageDao = storageDao;
    }

    private StorageDao storageDao;

    @Transactional
    public void createIngradient() throws IOException {
        Set<Storage> allIngradients = new HashSet<>(storageDao.findAll());
        Storage storage = new Storage();
        System.out.println("Adding new ingradient to data base");
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter name of new ingradient:");
        String name =  br.readLine().toLowerCase();
        storage.setName(name);
        System.out.println("Enter numerosity of ingradients:");
        int numerosity =  Integer.parseInt(br.readLine());
        storage.setNumerosity(numerosity);
         if (!allIngradients.contains(storage)) {
             storageDao.save(storage);
            System.out.println("A new ingradient was saved to data-base successfully!!!");
        }
    }

    @Transactional
    public List<Storage> getAllIngradients()
    {
        return storageDao.findAll();
    }

    @Transactional
    public List<Storage> getEndedIngradients()
    {
        return storageDao.printEnded();
    }

    @Transactional
    public Storage getIngradientByName() throws  IOException
    {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter name of ingradient you are going to find:");
        String name = br.readLine();
        return storageDao.findByName(name);
    }


    @Transactional
    public void deleteIngradient() throws  IOException
    {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter id of  ingradient you are going to delete:");
        long id = Long.parseLong(br.readLine());
        storageDao.remove(id);
        System.out.println("Ingradient was deleted successfully!");
    }


    @Transactional
    public void changeNumerosity() throws  IOException
    {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter id of  ingradient you are going to change numerosity:");
        long id = Long.parseLong(br.readLine());
        System.out.println("Enter  new numerosity of this ingradient:");
        int numerosity =  Integer.parseInt(br.readLine());
        storageDao.changeNumerosity(id,numerosity);
        System.out.println("Ingradient was changed successfully!");
    }


}
