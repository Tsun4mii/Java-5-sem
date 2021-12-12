package bstu.shust.jprojectv2.dto;

public class IdRentRequest {
    private long id;
    private boolean rent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isRent() {
        return rent;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }
}
