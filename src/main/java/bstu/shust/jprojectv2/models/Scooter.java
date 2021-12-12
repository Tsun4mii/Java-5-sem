package bstu.shust.jprojectv2.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "scooter")
@Data
@Getter
@Setter
public class Scooter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Date expirationDate;

    @Column
    private int cost;

    public Scooter(){}

    public Scooter(String name, String description, int cost, Date date)
    {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.expirationDate = date;
    }

}
