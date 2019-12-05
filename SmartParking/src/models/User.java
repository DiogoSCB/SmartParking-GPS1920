package models;

import java.sql.Date;

public class User {

    private Integer idUser;
    private String name;
    private String licensePlate;
    private Date entryDate;
    private Date departureDate;
    private String email;
    private Integer idParkingSpace;
    private Integer idPark;

    public User(Integer idUser, String name, String licensePlate, Date entryDate, Date departureDate, String email, Integer idParkingSpace, Integer idPark) {
        this.idUser = idUser;
        this.name = name;
        this.licensePlate = licensePlate;
        this.entryDate = entryDate;
        this.departureDate = departureDate;
        this.email = email;
        this.idParkingSpace = idParkingSpace;
        this.idPark = idPark;
    }

    //Construtor sem o Id do Utilizador
    public User(String name, String licensePlate, Date entryDate, Date departureDate, String email, Integer idParkingSpace, Integer idPark) {
        this.name = name;
        this.licensePlate = licensePlate;
        this.entryDate = entryDate;
        this.departureDate = departureDate;
        this.email = email;
        this.idParkingSpace = idParkingSpace;
        this.idPark = idPark;
    }

    public User(String licensePlate, Integer idUser) {
        this.licensePlate= licensePlate;
        this.idUser=idUser;
    }


    public Integer getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public String getEmail() {
        return email;
    }

    public Integer getIdParkingSpace() {
        return idParkingSpace;
    }

    public Integer getIdPark() {
        return idPark;
    }
}
