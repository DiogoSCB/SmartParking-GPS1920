package Models;

import DataBase.DBConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data implements Constants
{
    Map<Park, List<Integer>> parks; /* TODO idealmente aqui sera Map<Park, List<ParkingSpace>> */
    List<Request> requests;
    List<User> users;
    DBConnection dbConnection;

    public Data()
    {
        dbConnection = new DBConnection(ip, port);

        requests = dbConnection.getRequestList();
        users = dbConnection.getUserList();
        parks = new HashMap<>();

        for (Park p : dbConnection.getParkList())
        {
            ArrayList<Integer> parkingSpaces = dbConnection.getFreeParkingSpaces(p.getIdPark());
            parks.put(p, parkingSpaces);
        }

        /* TODO continue data init here */
    }
}
