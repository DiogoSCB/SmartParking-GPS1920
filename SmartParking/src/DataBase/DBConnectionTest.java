package DataBase;

import Models.Park;
import Models.Request;
import Models.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {
    DBConnection dbConnection;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
         dbConnection = new DBConnection("localhost", "3306");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
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
            System.out.print(p.getEntryData() + " ");
            System.out.print(p.getDepartureData() + " ");
            System.out.print(p.getEmail() + " ");
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
    }

    @org.junit.jupiter.api.Test
    void addRequest() {
    }

    @org.junit.jupiter.api.Test
    void removeUser() {
    }

    @org.junit.jupiter.api.Test
    void modifyUser() {
    }

    @org.junit.jupiter.api.Test
    void modifyRequest() {
    }

    @org.junit.jupiter.api.Test
    void getFreeParkingSpaces() {
    }

    @org.junit.jupiter.api.Test
    void getParkingSpaces() {
    }

    @org.junit.jupiter.api.Test
    void addUserParkingSpace() {
    }
}