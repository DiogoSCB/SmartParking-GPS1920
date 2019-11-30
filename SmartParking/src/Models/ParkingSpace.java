package models;

public class ParkingSpace {

    private Integer idParkingSpace;
    private int reserved;
    private Integer idPark;

    public ParkingSpace(Integer idParkingSpace, int reserved, Integer idPark) {
        this.idParkingSpace = idParkingSpace;
        this.reserved = reserved;
        this.idPark = idPark;
    }

    public Integer getIdParkingSpace() {
        return idParkingSpace;
    }

    public int getReserved() {
        return reserved;
    }

    public Integer getIdPark() {
        return idPark;
    }
}
