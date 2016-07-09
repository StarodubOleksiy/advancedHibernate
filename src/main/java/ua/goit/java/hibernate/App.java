package ua.goit.java.hibernate;
        import org.springframework.beans.factory.BeanCreationException;
        import org.springframework.context.ApplicationContext;
        import org.springframework.context.support.ClassPathXmlApplicationContext;
        import ua.goit.java.hibernate.model.Controllers.*;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.lang.reflect.InvocationTargetException;
        import java.util.ArrayList;
        import java.util.List;


public class App
{
    private EmployeeController employeeController;
    private DishController dishController;
    private OrderController orderController;
    private StorageController storageController;
    private CookedDishController cookedDishController;
    private boolean reInit;



    public void setReInit(boolean reInit) {
        this.reInit = reInit;
    }


    public void setStorageController(StorageController storageController) {
        this.storageController = storageController;
    }

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }

    public void setCookedDishController(CookedDishController cookedDishController) {
        this.cookedDishController = cookedDishController;
    }



    public void init() {
        if(reInit)
        {
            employeeController.initEmployees();
        }
    }



    public static void main( String[] args )  throws IOException
    {

        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml", "hibernate-context.xml");
            App app = context.getBean(App.class);
               BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
           // app.cookedDishController.printIngradient();
             while(true)
            {
                System.out.println("Enter the word to table you want to work:");
                System.out.println("Employees-If you want to work with employees:");
                System.out.println("Dishes-If you want to work with dishes:");
                System.out.println("Orders-If you want to work with booking:");
                System.out.println("Storage-If you want to work with storage:");
                System.out.println("CookedDishes-If you want to work with CookedDishes:");
                String command = br.readLine().toLowerCase();

                if(command.equals("employees"))
                {
                    while (true) {
                        System.out.println("Enter the word what are you going to do:");
                        System.out.println("Add - to add new employee into data base:");
                        System.out.println("Delete -  to delete new employee into data base:");
                        System.out.println("view -  to view all employees into data base:"); //Fetch
                        System.out.println("viewwaiters -  to view all waiters into data base:");
                        System.out.println("viewcooks -  to view all waiters into data base:");
                        System.out.println("Find -  to find employee by name:");
                        String newCommand = br.readLine().toLowerCase();
                        if (newCommand.equals("add")) {
                            app.employeeController.createEmployee();
                        } else if (newCommand.equals("delete")) {
                            app.employeeController.deleteEmployees();
                        } else if (newCommand.equals("viewwaiters")) {
                            app.employeeController.printWaiters();
                        } else if (newCommand.equals("viewcooks")) {
                            app.employeeController.printCooks();
                        } else if (newCommand.equals("view")) {
                            app.employeeController.getAllEmployees().forEach(System.out::println);
                        }
                        else if (newCommand.equals("find")) {
                            System.out.println(app.employeeController.getEmployeesByName());
                        } else break;
                    }

                } else if(command.equals("dishes"))
                {
                    while (true) {
                        System.out.println("Enter the word what are you going to do:");
                        System.out.println("Add - to add new dish into data base:");
                        System.out.println("Delete -  to delete new dish into data base:");
                        System.out.println("View -  to view all dishes into data base:");
                        System.out.println("Find -  to find dish by name:");
                        String newCommand = br.readLine().toLowerCase();
                        if (newCommand.equals("add")) {
                            app.dishController.createDish();
                        } else if (newCommand.equals("delete")) {
                            app.dishController.deleteDishById();
                        } else if (newCommand.equals("view")) {
                            app.dishController.getAllDishes().forEach(System.out::println);
                        } else if (newCommand.equals("find")) {
                            System.out.println(app.dishController.getDishByName());
                        } else break;
                    }
                }  else if(command.equals("orders"))
                {
                    while (true) {
                        System.out.println("Add -  to add new booking into data base:");
                        System.out.println("SetClose -  to set booking close:");
                        System.out.println("Delete -  to delete booking from data base:");
                        System.out.println("ViewOpen -  to view all open booking into data base:");
                        System.out.println("ViewClose -  to view all close booking into data base:");
                        System.out.println("AddDishes -  to add new dishes to order:");
                        System.out.println("RemoveDishes -  to remove dishes to order:");
                        String newCommand = br.readLine().toLowerCase();
                        if (newCommand.equals("add")) {
                            app.orderController.createOrder();
                        } else if(newCommand.equals("setclose")) {
                            app.orderController.setClose();
                        } else if(newCommand.equals("delete")) {
                            app.orderController.deleteOrder();
                        } else if(newCommand.equals("viewopen")) {
                            app.orderController.printAllOrders();
                        }  else if (newCommand.equals("viewclose")) {
                            app.orderController.printCloseOrders();
                        } else if (newCommand.equals("adddishes")) {
                            app.orderController.addNewDishes();
                        }  else if (newCommand.equals("removedishes")) {
                            app.orderController.deleteDishes();
                        }

                        else break;
                    }

                } else if(command.equals("storage"))
                {
                    while (true) {
                        System.out.println("Add -  to add new ingradient into data base:");
                        System.out.println("Delete -  to delete ingradient from data base:");
                        System.out.println("View -  to view all ingradients into data base:");
                        System.out.println("Find -  to find ingradient by name:");
                        System.out.println("Change -  to change numerosity of ingradients in storage:");
                        System.out.println("Ended -  to view all ingradients into data base that ended:");
                        String newCommand = br.readLine().toLowerCase();
                        if (newCommand.equals("add")) {
                            app.storageController.createIngradient();
                        } else if(newCommand.equals("delete")) {
                            app.storageController.deleteIngradient();
                        } else if (newCommand.equals("view")) {
                            app.storageController.getAllIngradients().forEach(System.out::println);
                        } else if (newCommand.equals("find")) {
                            System.out.println(app.storageController.getIngradientByName());
                        } else if (newCommand.equals("change")) {
                            app.storageController.changeNumerosity();
                        }  else if (newCommand.equals("ended")) {
                            app.storageController.getEndedIngradients().forEach(System.out::println);
                        } else break;

                    }
                }else if(command.equals("cookeddishes"))
                {
                    while (true) {
                        System.out.println("Add -  to add new cooked dish into data base:");
                        System.out.println("View -  to see cooked dishes into data base:");
                        String newCommand = br.readLine().toLowerCase();
                        if (newCommand.equals("add")) {
                            app.cookedDishController.createCookedDish();
                        }  else if (newCommand.equals("view")) {
                            app.cookedDishController.getAllCookedDishes().forEach(System.out::println);
                        } else break;

                    }
                }

                else break;
            }
        } catch(BeanCreationException e)
        {
            System.out.println("What  I have to do?");
            e.printStackTrace();
        }
    }

}