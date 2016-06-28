package ua.goit.java.hibernate.model;





import javax.persistence.*;
import java.util.List;

/**
 * Created by Администратор on 26.06.16.
 */
@Entity
public class Waiter extends  Employee {

           @OneToMany()
           @JoinColumn(name = "employee_id")
            private List<Orders> orders;

      public List<Orders> getOrders() {return orders; }

     public void setOrders(List<Orders> orders) {this.orders= orders;  }

           @Override
            public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Waiter (\n");
            sb.append("id = (\n").append(getId()).append("\n");
            sb.append("name = (\n").append(getName()).append("\n");
            sb.append("surname = (\n").append(getSurname()).append("\n");
            sb.append("orders = (\n");
            orders.forEach(orders -> sb.append("  ").append(orders).append("\n"));
            sb.append(" )\n");
            sb.append(")\n");
            return sb.toString();
            }
}
