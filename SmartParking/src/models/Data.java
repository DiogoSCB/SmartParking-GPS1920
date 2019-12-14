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

    public ArrayList<String> getParkNames() {
        importParks();
        ArrayList<String> parkNames = new ArrayList<>();

        for (Park p: parks.keySet())
            parkNames.add(p.getNamePark());

        Collections.sort(parkNames);
        return parkNames;
    }

    public ObservableList<RequestRow> getRequestByParkName(String name) {
        requests = dbConnection.getRequestList();
        users = dbConnection.getUserList();

        ArrayList<RequestRow> requestRows = new ArrayList<>();
        for (Request r: requests) {
            User u = getUserByID(r.getIdUser());
            if (u.getName().equals(name))
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

    public ObservableList<User> getUserByParkName(String name) {
        users = dbConnection.getUserList();
        ArrayList<User> usersByParkName = new ArrayList<>();

        for (User u : users)
            if (getParkByID(u.getIdPark()).getNamePark().equals(name))
                usersByParkName.add(u);

        return FXCollections.observableArrayList(usersByParkName);
    }

    public Park getParkByID(int id) {
        for (Park p : parks.keySet())
            if (p.getIdPark() == id)
                return p;

        return null;
    }

    public ObservableList<Integer> getParkingFreeSpacesByName(String name) {

        ArrayList<Integer> freeSpaces = new ArrayList<>();
        for (Park p : parks.keySet())
            if (p.getNamePark().equals(name))
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

    public ParkingSpace getParkingSpace(Integer idPark, Integer idPS) {
        for (Park p : parks.keySet())
            if (p.getIdPark().equals(idPark))
                for (ParkingSpace ps : parks.get(p))
                    if (ps.getIdParkingSpace().equals(idPS)) {
                        return ps;
                    }
        return null;
    }

    public void setReserved(Integer idPark, Integer idPSOld, Integer idPSNew) {
        for (Park p : parks.keySet())
            if (p.getIdPark().equals(idPark))
                for (ParkingSpace ps : parks.get(p)) {
                    if (ps.getIdParkingSpace().equals(idPSNew))
                        ps.setReserved(true);
                    if (ps.getIdParkingSpace().equals(idPSOld))
                        ps.setReserved(false);
                }
    }

    public void modifyUser(Integer idUser, String name, String licensePlate, String email, Integer idParkingSpace) {
        for (User u : users) {
            if (u.getIdUser().equals(idUser)) {
                u.setName(name);
                u.setLicensePlate(licensePlate);
                u.setEmail(email);
                if (!u.getIdParkingSpace().equals(idParkingSpace)) {
                    Integer old = u.getIdParkingSpace();
                    u.setIdParkingSpace(idParkingSpace);
                    setReserved(u.getIdPark(), u.getIdParkingSpace(), old);
                    dbConnection.addUserParkingSpace(u, getParkingSpace(u.getIdPark(), u.getIdParkingSpace()), old);
                } else dbConnection.modifyUser(u);
            }
        }
    }

    public void modifyRequest(Request request, int state) {
        request.setState(state);
        dbConnection.modifyRequest(request);
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
