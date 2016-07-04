package ua.goit.java.hibernate.model;




import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


/**
 * Created by Администратор on 04.05.16.
 */

@Entity
@Table(name = "EMPLOYEE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class Employee {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment" , strategy = "increment")
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Position position;
    @Column(name = "salary")
    private Float salary;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }
    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    public Position getPosition() {
        return position;
    }
    @Column(name = "salary")
    public Float getSalary() {
        return salary;
    }

    public void setId(Long id) {        this.id = id;    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }


   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(employee.phoneNumber) : employee.phoneNumber != null)
            return false;
        if (position != employee.position) return false;
        if (salary != null ? !salary.equals(employee.salary) : employee.salary != null) return false;
        if (surname != null ? !surname.equals(employee.surname) : employee.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", ID='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone_number=" + phoneNumber +
                ", salary=" + salary +
                ", position='" + position + '\'' +
                '}'; }


}
