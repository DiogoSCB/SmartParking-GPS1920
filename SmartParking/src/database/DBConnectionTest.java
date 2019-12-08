package database;

import models.Park;
import models.ParkingSpace;
import models.Request;
import models.User;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
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
            System.out.print(p.getNamePark() + " ");
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
        dbConnection.addUser(new User("Beatriz", "1101PZ", Date.valueOf("2019-12-01"), null, "biazinha@gmail.com", null, 1,"Clientes"));
        dbConnection.addUser(new User("Carlos", "12II88", null, null, "carlinho@gmail.com", null, 2,"Clientes"));
    }



    @org.junit.jupiter.api.Test
    void removeUser() {
        dbConnection.removeUser(new User(2, "Beatriz", "1101PZ", Date.valueOf("2019-12-01"), null, "biazinha@gmail.com", null, 1));
    }

   /* @org.junit.jupiter.api.Test
    void modifyUser() {
        dbConnection.modifyUser(new User("Carlos", "12II88", null, null, "carlinho@gmail.com", null, 2,"CoimbraB"));
    }*/

    @org.junit.jupiter.api.Test
    void modifyRequest() {
        dbConnection.modifyRequest(new Request(0,0));
    }

    @org.junit.jupiter.api.Test
    void getFreeParkingSpaces() {
        dbConnection.getFreeParkingSpaces(new Park(1, "Clientes"));
    }

    @org.junit.jupiter.api.Test
    void getParkingSpaces() {
        dbConnection.getParkingSpaces(new Park(1, "Clientes"));
    }

    @org.junit.jupiter.api.Test
    void addUserParkingSpace() {

        dbConnection.addUserParkingSpace(new User("12II88",2), new ParkingSpace(1,1, 2));


    }
}
