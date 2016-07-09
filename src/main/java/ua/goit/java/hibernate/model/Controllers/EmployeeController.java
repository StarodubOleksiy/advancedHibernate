package ua.goit.java.hibernate.model.Controllers;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.hibernate.model.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeController {
    private EmployeeDao employeeDao;

    @Transactional
    public void createEmployee() throws IOException
    {
     Set<Employee> allEmployees = new HashSet<>(employeeDao.findAll());
        Employee employee;
        System.out.println("Adding new employee to data base");
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter position of new employee:");
        String position =  br.readLine().toLowerCase();
        if(position.equals("waiter")) {
            employee  = new Waiter();
            employee.setPosition(Position.WAITER);
        }
        else if(position.equals("cook")) {
            employee  = new Cook();
            employee.setPosition(Position.COOK);
        }
      /*  else if(position.equals("manager"))
            employee.setPosition(Position.MANAGER);*/
        else throw new IOException("You enter invalid position");
        System.out.println("Enter id of new employee:");
        long id = Long.parseLong(br.readLine());
        employee.setId(id);
        System.out.println("Enter name of new employee:");
        String name =  br.readLine();
        employee.setName(name);
        System.out.println("Enter surname of new employee:");
        String surName =  br.readLine();
        employee.setSurname(surName);

        System.out.println("Enter phone number of new employee:");
        String phone =  br.readLine();
        employee.setPhoneNumber(phone);
        System.out.println("Enter salary of new employee:");
        float salary =  Float.parseFloat(br.readLine());
        employee.setSalary(salary);
      if (!allEmployees.contains(employee)) {
            employeeDao.save(employee);
          System.out.println("A new employee was saved to data-base successfully!!!");
        }
      }

    @Transactional
    public Employee getEmployeesByName() throws  IOException
    {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter name of employee you are going to find:");
        String name = br.readLine();
        return employeeDao.findByName(name);
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

   @Transactional
    public List<Employee> getAllEmployees()
{
  return employeeDao.findAll();
}


    @Transactional
    public void deleteEmployees() throws  IOException
    {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        System.out.println("Enter id of  employee you are going to delete:");
        long id = Long.parseLong(br.readLine());
       employeeDao.remove(id);
        System.out.println("Employee was deleted successfully!");
    }

    @Transactional
    public void initEmployees()
    {
        System.out.println("Init employees.");

      Waiter john = new Waiter();
        john.setName("John");
        john.setSurname("Smith");
        john.setPhoneNumber("555-55-55");
        john.setPosition(Position.WAITER);
        john.setSalary(25000.00F);
        employeeDao.save(john);

        Waiter mary = new Waiter();
        mary.setName("Mary");
        mary.setSurname("Marlyn");
        mary.setPhoneNumber("666-66-66");
        mary.setPosition(Position.WAITER);
        mary.setSalary(25000.00F);
        employeeDao.save(mary);

        Cook james = new Cook();
        james.setName("James");
        james.setSurname("Bond");
        james.setPhoneNumber("777-77-77");
        james.setPosition(Position.COOK);
        james.setSalary(35000.00F);
        employeeDao.save(james);
    }


    @Transactional
    public void printWaiters()
    {
        employeeDao.findAllWaiters().forEach(System.out::println);
    }

    @Transactional
    public void printCooks()
    {
        employeeDao.findAllCooks().forEach(System.out::println);
    }

    @Transactional
    public void printEmployee(long id)
    {
       System.out.println(employeeDao.load(id));
       System.out.println(employeeDao.load(id));
    }



}
