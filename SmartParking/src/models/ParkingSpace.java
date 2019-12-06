package models;

import java.sql.Date;

public class ParkingSpace {

    private Integer idParkingSpace;
    private int reserved;
    private Integer idPark;
    private String licensePlate;
    private Integer idUser;

    public ParkingSpace(Integer idParkingSpace, int reserved, Integer idPark) {
        this.idParkingSpace = idParkingSpace;
        this.reserved = reserved;
        this.idPark = idPark;
    }

    /*public ParkingSpace(String licensePlate,  int reserved, Integer idParkingSpace, Integer idPark, Integer idUser) {
        this.licensePlate = licensePlate;
        this.reserved=reserved;
        this.idParkingSpace = idParkingSpace;
        this.idPark = idPark;
        this.idUser=idUser;
    }
*/


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
