package models;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class Data implements Constants {

    private Map<Park, List<ParkingSpace>> parks;
    private ArrayList<Request> requests;
    private ArrayList<User> users;
    private DBConnection dbConnection;

    public Data() {
        dbConnection = new DBConnection(ip, port);
        importAllData();
    }

    private void importAllData() {
        requests = dbConnection.getRequestList();
        users = dbConnection.getUserList();
        parks = new HashMap<>();

        for (Park p : dbConnection.getParkList()) {
            ArrayList<ParkingSpace> parkingSpaces = dbConnection.getParkingSpaces(p);
            parks.put(p, parkingSpaces);
        }
    }

    public ArrayList<Integer> getParkIdAsIntegers()
    {
        ArrayList<Integer> parksIDS = new ArrayList<>();
        for (Park p : parks.keySet())
            parksIDS.add(p.getIdPark());

        Collections.sort(parksIDS);
        return parksIDS;
    }

    public ObservableList<Request> getRequests() {
        return FXCollections.observableArrayList(requests);
    }

    public ObservableList<User> getUsers() {
        return FXCollections.observableArrayList(users);
    }

    public ObservableList<UserRow> getUsersByParkID(int parkID) {
        ArrayList<UserRow> usersByParkID = new ArrayList<>();

        for (User u :users)
            if (u.getIdPark() == parkID)
                usersByParkID.add(new UserRow(u));

        return FXCollections.observableArrayList(usersByParkID);
    }

}
