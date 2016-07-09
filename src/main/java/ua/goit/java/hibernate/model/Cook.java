package ua.goit.java.hibernate.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Администратор on 07.07.16.
 */
@Entity
public class Cook extends Employee {
    @OneToMany(mappedBy = "cook", fetch = FetchType.EAGER)
    //@OneToMany(mappedBy = "waiter", fetch = FetchType.LAZY)
    //@OneToMany
    //@JoinColumn(name = "employee_id")
    @Fetch(FetchMode.JOIN)
    private List<Cooked_Dish> dishes;

    public List<Cooked_Dish> getDishes() {return dishes; }

    public void setDishes(List<Cooked_Dish> dishes) {this.dishes= dishes;  }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cook (\n");
        sb.append("id = (\n").append(getId()).append("\n");
        sb.append("name = (\n").append(getName()).append("\n");
        sb.append("surname = (\n").append(getSurname()).append("\n");
        sb.append("dishes = (\n");
        dishes.forEach(dishes -> sb.append("  ").append(dishes).append("\n"));
        sb.append(" )\n");
        sb.append(")\n");
        return sb.toString();
    }

}
