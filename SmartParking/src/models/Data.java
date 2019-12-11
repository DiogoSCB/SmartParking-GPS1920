package models;

import database.DBConnection;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class Data implements Constants {

    private Map<Park, ArrayList<ParkingSpace>> parks;
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
        importParks();
    }

    private void importParks() {
        parks = new HashMap<>();

        for (Park p : dbConnection.getParkList()) {
            ArrayList<ParkingSpace> parkingSpaces = dbConnection.getParkingSpaces(p);
            parks.put(p, parkingSpaces);
        }
    }

    public ArrayList<Integer> getParkIdAsIntegers() {
        importParks();
        ArrayList<Integer> parksIDS = new ArrayList<>();
        for (Park p : parks.keySet())
            parksIDS.add(p.getIdPark());

        Collections.sort(parksIDS);
        return parksIDS;
    }

    public ObservableList<RequestRow> getRequests() {
        requests = dbConnection.getRequestList();
        users = dbConnection.getUserList();

        ArrayList<RequestRow> requestRows = new ArrayList<>();
        for (Request r: requests) {
            User u = getUserByID(r.getIdUser());
            requestRows.add(new RequestRow(r, u));
        }

        return FXCollections.observableArrayList(requestRows);
    }

    public ObservableList<User> getUsers() {
        users = dbConnection.getUserList();
        return FXCollections.observableArrayList(users);
    }

    public ObservableList<User> getUsersByParkID(int parkID) {
        users = dbConnection.getUserList();
        ArrayList<User> usersByParkID = new ArrayList<>();

        for (User u : users)
            if (u.getIdPark() == parkID)
                usersByParkID.add(u);

        return FXCollections.observableArrayList(usersByParkID);
    }

    public ObservableList<Integer> getParkingFreeSpacesById(int id) {

        ArrayList<Integer> freeSpaces = new ArrayList<>();
        for (Park p: parks.keySet())
            if (p.getIdPark() == id)
                freeSpaces = dbConnection.getFreeParkingSpaces(p);


        return FXCollections.observableArrayList(freeSpaces);
    }

    private User getUserByID(int id) {
        for (User u: users)
            if (u.getIdUser() == id)
                return u;
        return null;
    }

    public void removeUser(User user) {
        users.remove(user);
        dbConnection.removeUser(user);
    }

    public void modifyRequest(Request request, int state) {
        request.setState(state);
        dbConnection.modifyRequest(request);
    }

    public void modifyUser(Integer id, String name, String licensePlate, String email, Integer idParkingSpace) {
        for (User u : users) {
            if (u.getIdUser().equals(id)) {
                u.setName(name);
                u.setLicensePlate(licensePlate);
                u.setEmail(email);
                if (u.getIdParkingSpace().equals(0))
                    u.setIdParkingSpace(null);
                else
                    u.setIdParkingSpace(idParkingSpace);
                dbConnection.modifyUser(u);
            }
        }
    }

    public boolean validateLicensePlate(String licensePlate) {
        if (licensePlate.isBlank() || licensePlate.isEmpty()) return false;
        if (licensePlate.length() != 6) return false;
        if (licensePlate.contains(" ")) return false;

        CharSequence first = licensePlate.substring(0, 2);
        if (!Character.isDigit(first.charAt(0)) || !Character.isDigit(first.charAt(1))) return false;

        CharSequence second = licensePlate.substring(2, 4);
        if (!Character.isAlphabetic(second.charAt(0)) || !Character.isAlphabetic(second.charAt(1))) return false;

        CharSequence third = licensePlate.substring(4, 6);
        return Character.isDigit(third.charAt(0)) || Character.isDigit(third.charAt(1));
    }

    public boolean validateNome(String name) {
        if (name.isBlank() || name.isEmpty()) return false;

        for (char c : name.toCharArray()) {
            if (Character.isDigit(c)) //se tem número
                return false;
        }
        return name.length() >= 3 && name.length() <= 40;
    }

    public boolean validateEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"; //formato manhoso
        return email.matches(regex);
    }
}
