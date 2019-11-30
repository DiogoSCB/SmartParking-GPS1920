package Models;

import java.sql.Date;

public class User {

    private String name;
    private String licensePlate;
    private Date entryData;
    private Date departureData;
    private String email;
    private int idParkingSpace;
    private int idPark;
    private int idUser;

    public User(String name, String licensePlate, Date entryData, Date departureData, String email, int idPark,int idUser) {
        this.name = name;
        this.licensePlate = licensePlate;
        this.entryData = entryData;
        this.departureData = departureData;
        this.email = email;
        this.idParkingSpace = idParkingSpace;
        this.idPark = idPark;
        this.idUser=idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        licensePlate = licensePlate;
    }

    public Date getEntryData() {
        return entryData;
    }

    public void setEntryData(Date entryData) {
        entryData = entryData;
    }

    public Date getDepartureData() {
        return departureData;
    }

    public void setDepartureData(Date departureData) {
        departureData = departureData;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email;
    }

    public int getIdParkingSpace() {
        return idParkingSpace;
    }

    public void setIdParkingSpace(int idParkingSpace) {
        this.idParkingSpace = idParkingSpace;
    }

    public int getIdPark() {
        return idPark;
    }

    public void setIdPark(int idPark) {
        this.idPark = idPark;
    }

    public int getIdUser(){
        return idUser;
    }

    public void setIdUser(int idUser){ this.idUser=idUser;}
}
