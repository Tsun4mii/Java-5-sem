package bstu.shust.jprojectv2.dto;

import lombok.Data;
import lombok.Getter;

import java.util.Date;
@Data
@Getter
public class ScooterRequestNoRent {
    private long id;
    private String name;
    private String description;
    private int cost;
    private Date expirationDate;
}
