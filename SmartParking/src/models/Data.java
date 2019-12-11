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

    public ArrayList<Integer> getParkIdAsIntegers() {
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

    public ObservableList<User> getUsersByParkID(int parkID) {
        ArrayList<User> usersByParkID = new ArrayList<>();

        for (User u : users)
            if (u.getIdPark() == parkID)
                usersByParkID.add(u);

        return FXCollections.observableArrayList(usersByParkID);
    }

    public void removeUser(User user) {
        users.remove(user);
        dbConnection.removeUser(user);
    }

    public void modifyUser(Integer id, String name, String licensePlate, String email) {
        for (User u : users) {
            if (u.getIdUser().equals(id)) {
                u.setName(name);
                u.setLicensePlate(licensePlate);
                u.setEmail(email);
                if (u.getIdParkingSpace().equals(0))
                    u.setIdParkingSpace(null);
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
