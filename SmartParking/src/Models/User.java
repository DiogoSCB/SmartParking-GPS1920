package Models;

import java.sql.Date;

public class User {

    private String name;
    private int idUser;
    private String licensePlate;
    private Date entryData;
    private Date departureData;
    private String email;
    private int park;

    public User(String name, int idUser, String licensePlate, Date entryData, Date departureData, String email, int park) {
        this.name = name;
        idUser = idUser;
        licensePlate = licensePlate;
        entryData = entryData;
        departureData = departureData;
        email = email;
        park = park;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        idUser = idUser;
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

    public int getPark() {
        return park;
    }

    public void setPark(int park) {
        park = park;
    }
}
