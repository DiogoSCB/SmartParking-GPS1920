package database;

import models.Park;
import models.ParkingSpace;
import models.Request;
import models.User;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static models.Constants.ip;
import static models.Constants.port;

class DBConnectionTest {

    private DBConnection dbConnection;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        dbConnection = new DBConnection(ip, port);
    }

    @org.junit.jupiter.api.Test
    void getParkList() {
        ArrayList<Park> parks = dbConnection.getParkList();
        for (Park p : parks) {
            System.out.print(p.getIdPark() + " ");
            System.out.print(p.getTotalParkingSpace() + " ");
            System.out.print(p.getFreeParkingSpace() + " ");
            System.out.println();
        }
    }

    @org.junit.jupiter.api.Test
    void getUserList() {
        ArrayList<User> users = dbConnection.getUserList();
        for (User p : users) {
            System.out.print(p.getIdUser() + " ");
            System.out.print(p.getLicensePlate() + " ");
            System.out.print(p.getEntryDate() + " ");
            System.out.print(p.getDepartureDate() + " ");
            System.out.print(p.getEmail() + " ");
            System.out.print(p.getIdParkingSpace() + " ");
            System.out.print(p.getIdPark() + " ");
            System.out.println();
        }
    }

    @org.junit.jupiter.api.Test
    void getRequestList() {
        ArrayList<Request> requests = dbConnection.getRequestList();
        for (Request p : requests) {
            System.out.print(p.getIdRequest() + " ");
            System.out.print(p.getRequestDate() + " ");
            System.out.print(p.getState() + " ");
            System.out.print(p.getIdUser() + " ");
            System.out.println();
        }
    }

    @org.junit.jupiter.api.Test
    void addUser() {
        dbConnection.addUser(new User("Beatriz", "1101PZ", Date.valueOf("2019-12-01"), null, "biazinha@gmail.com", null, 1));
        dbConnection.addUser(new User("Carlos", "12II88", null, null, "carlinho@gmail.com", null, 2));
    }


    @Test
    void addRequest() {
        dbConnection.addRequest(new Request(Date.valueOf("2019-12-05"),0,3));
    }

    @org.junit.jupiter.api.Test
    void removeUser() {
        dbConnection.removeUser(new User(2,"Beatriz", "1101PZ", Date.valueOf("2019-12-01"), null, "biazinha@gmail.com", null, 1));
    }

    @org.junit.jupiter.api.Test
    void modifyUser() {
        dbConnection.modifyUser(new User("Carlos", "12II88", null, null, "carlinho@gmail.com", null, 2));
    }

    @org.junit.jupiter.api.Test
    void modifyRequest() {
        dbConnection.modifyRequest(new Request(2,null,0,2));
    }

    @org.junit.jupiter.api.Test
    void getFreeParkingSpaces() {
        dbConnection.getFreeParkingSpaces(new Park(1,100,50));
    }

    @org.junit.jupiter.api.Test
    void getParkingSpaces() {
        dbConnection.getParkingSpaces(new Park(1,100,50));
    }

    @org.junit.jupiter.api.Test
    void addUserParkingSpace() {

        dbConnection.addUserParkingSpace(new User("21II18",2), new ParkingSpace(2,1,2));


    }
}