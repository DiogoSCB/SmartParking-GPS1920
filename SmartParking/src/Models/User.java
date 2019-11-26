package Models;

import java.sql.Date;

public class User {

    private String name;
    private String licensePlate;
    private Date entryData;
    private Date departureData;
    private String email;

    public User(String name, String licensePlate, Date entryData, Date departureData, String email) {
        this.name = name;
        this.licensePlate = licensePlate;
        this.entryData = entryData;
        this.departureData = departureData;
        this.email = email;
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
}
