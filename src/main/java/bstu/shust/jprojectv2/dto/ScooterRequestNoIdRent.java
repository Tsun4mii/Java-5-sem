package bstu.shust.jprojectv2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ScooterRequestNoIdRent {
    private String name;
    private String description;
    private int cost;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expirationDate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
