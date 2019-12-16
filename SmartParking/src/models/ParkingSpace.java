package models;


public class ParkingSpace {

    private Integer idParkingSpace;
    private boolean reserved;
    private Integer idPark;

    public ParkingSpace(Integer idParkingSpace, boolean reserved, Integer idPark) {
        this.idParkingSpace = idParkingSpace;
        this.reserved = reserved;
        this.idPark = idPark;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public Integer getIdParkingSpace() {
        return idParkingSpace;
    }

    public boolean isReserved() {
        return reserved;
    }

    public Integer getIdPark() {
        return idPark;
    }
}
