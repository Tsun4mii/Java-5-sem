package bstu.shust.jprojectv2.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_rent")
@Data
@Getter
@Setter
public class RentForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String userName;

    @Column
    private String userSurname;

    @ManyToOne
    @JoinColumn(name = "scooter_id")
    private Scooter scooter;

    @Column
    private boolean rent;

    public RentForm(){}
    public RentForm(User user, String userName, String userSurname, Scooter scooter)
    {
        this.user = user;
        this.userName = userName;
        this.userSurname = userSurname;
        this.scooter = scooter;
    }
}
