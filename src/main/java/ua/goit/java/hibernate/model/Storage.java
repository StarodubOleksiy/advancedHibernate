package ua.goit.java.hibernate.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Администратор on 20.06.16.
 */
@Entity
@Table(name = "STORAGE")
public class Storage {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment" , strategy = "increment")
    @Column(name = "id")
    private long Id;

    @Column(name = "name")
    private String name;

    @Column(name = "numerosity")
    private int numerosity;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumerosity() {
        return numerosity;
    }

    public void setNumerosity(int numerosity) {
        this.numerosity = numerosity;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", numerosity=" + numerosity +
                '}';
    }

}
