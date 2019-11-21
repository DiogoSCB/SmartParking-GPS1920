package Models;

import java.sql.Date;

public class User {

    private String name;
    private int IdUser;
    private String LicensePlate;
    private Date EntryData;
    private Date DepartureData;
    private String Email;
    private int Park;

    public User(String name, int idUser, String licensePlate, Date entryData, Date departureData, String email, int park) {
        this.name = name;
        IdUser = idUser;
        LicensePlate = licensePlate;
        EntryData = entryData;
        DepartureData = departureData;
        Email = email;
        Park = park;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }

    public String getLicensePlate() {
        return LicensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        LicensePlate = licensePlate;
    }

    public Date getEntryData() {
        return EntryData;
    }

    public void setEntryData(Date entryData) {
        EntryData = entryData;
    }

    public Date getDepartureData() {
        return DepartureData;
    }

    public void setDepartureData(Date departureData) {
        DepartureData = departureData;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getPark() {
        return Park;
    }

    public void setPark(int park) {
        Park = park;
    }
}
