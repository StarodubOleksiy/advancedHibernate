package ua.goit.java.hibernate.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Администратор on 05.07.16.
 */
@Entity
@Table(name = "COOKED_DISH")

public class Cooked_Dish {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment" , strategy = "increment")
    @Column(name = "id")
    private long id;
    @OneToOne
    @JoinColumn(name = "dish_id")

    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Cook  cook;


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @Column(name = "date")
    private String orderDate;


    public void setDish(Dish dish) {
        this.dish = dish;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }


    @Override
    public String toString() {
        return "CookedDishes{" +
                "id=" + id +
                ", dish=" + dish +
               // ", waiter=" + waiter +
                ", order=" + order +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cooked_Dish that = (Cooked_Dish) o;

        if (dish != null ? !dish.equals(that.dish) : that.dish != null) return false;
        if (order != null ? !order.equals(that.order) : that.order != null) return false;
        if (orderDate != null ? !orderDate.equals(that.orderDate) : that.orderDate != null) return false;
        if (cook != null ? !cook.equals(that.cook) : that.cook != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dish != null ? dish.hashCode() : 0;
        result = 31 * result + (cook != null ? cook.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        return result;
    }


}
