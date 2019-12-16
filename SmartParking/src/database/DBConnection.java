package database;

import models.Park;
import models.ParkingSpace;
import models.Request;
import models.User;

import java.sql.*;
import java.util.ArrayList;

import static models.Constants.*;

/**
 * @author Diogo Santos e Carolina Rosa
 */
public class DBConnection {

    private Connection connection; //Objeto para a ligação com a base de dados
    private Statement statement; //String SQL com o script pretendido
    private ResultSet resultSet; //Resultado vindo do script da String SQL

    /**
     * Construtor da Classe
     *
     * @param ip   é o IP do servidor da base de dados
     * @param port é o porto do servidor da base de dados
     */
    public DBConnection(String ip, String port) throws SQLException{
        System.out.println("Connecting to Database (" + ip + ":" + port + ")");

        connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + DB_NAME
                + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false" +
                "&serverTimezone=GMT", USER, PASS);
        System.out.println("Connection Established!");
        statement = connection.createStatement();
    }

    /**
     * Lista de todos os Parques registados na BD
     *
     * @return ArrayList<Park>
     */
    public ArrayList<Park> getParkList() {
        ArrayList<Park> parks = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Park";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) { //Enquanto houver dados
                parks.add(new Park(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return parks;
    }

    /**
     * Lista de todos os Utilizadores registados na BD
     *
     * @return ArrayList<User>
     */
    public ArrayList<User> getUserList() {
        ArrayList<User> users = new ArrayList<>();

        try {
            String sql = "SELECT * FROM User";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) { //Enquanto houver dados
                users.add(new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDate(4),
                        resultSet.getDate(5), resultSet.getString(6),
                        resultSet.getInt(7), resultSet.getInt(8)));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return users;
    }

    /**
     * Lista de todos os Pedidos registados na BD
     *
     * @return ArrayList<Request>
     */
    public ArrayList<Request> getRequestList() {
        ArrayList<Request> requests = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Request";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) { //Enquanto houver dados
                requests.add(new Request(resultSet.getInt(1), resultSet.getDate(2),
                        resultSet.getInt(3), resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return requests;
    }

    /**
     * Adicionar utilizador
     *
     * @param users é classe que encapsula a informação do Utilizador
     */
    public void addUser(User users) {
        String sql = "INSERT INTO User(name, licensePlate, entryDate, departureDate, email, idParkingSpace, idPark)"
                + " VALUES(?,?,?,?,?," + "idParkingSpace = " + users.getIdParkingSpace() + "idPark ="
                + users.getIdPark() + ")";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, users.getName());
            stmt.setString(2, users.getLicensePlate());
            stmt.setDate(3, users.getEntryDate());
            stmt.setDate(4, users.getDepartureDate());
            stmt.setString(5, users.getEmail());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Remover utilizador
     *
     * @param user é classe que encapsula a informação do Utilizador
     */
    public void removeUser(User user) {
        PreparedStatement stmt;

        try {
            String sql = "DELETE FROM Request WHERE idUser = ?";
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, user.getIdUser());
            stmt.executeUpdate();

            sql = "DELETE FROM User WHERE idUser = ? AND licensePlate= ?";
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, user.getIdUser());
            stmt.setString(2, user.getLicensePlate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Modificar os dados do utilizador
     *
     * @param user é classe que encapsula a informação do Utilizador
     */
    public void modifyUser(User user) {
        String sql = "UPDATE User SET name = ?, licensePlate = ?, entryDate = ?, departureDate = ?, email = ?, " +
                "idParkingSpace = " + user.getIdParkingSpace() + " WHERE IdUser = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLicensePlate());
            stmt.setDate(3, user.getEntryDate());
            stmt.setDate(4, user.getDepartureDate());
            stmt.setString(5, user.getEmail());
            stmt.setInt(6, user.getIdUser());


            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Alterar o estado do pedido
     *
     * @param request é classe que encapsula a informação do Pedido
     */
    public void modifyRequest(Request request) {
        String sql = "UPDATE Request SET state = ? WHERE idRequest = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, request.getState());
            stmt.setInt(2, request.getIdRequest());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Buscar a lista de os ids de todos os lugares de estacionamento livres de um determinado parque
     *
     * @param park é classe que encapsula a informação do Parque
     * @return a lista com os ids dos lugares de estacionamento
     */
    public ArrayList<Integer> getFreeParkingSpaces(Park park) {
        ArrayList<Integer> free = new ArrayList<>();

        try {
            String sql = "SELECT IdParkingSpace FROM ParkingSpace WHERE IdPark = " + park.getIdPark()
                    + " AND Reserved = " + false;
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) { //Enquanto houver dados
                free.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return free;
    }

    /**
     * Buscar a lista de todos os lugares de estacionamento livres de um determinado parque
     *
     * @param park é classe que encapsula a informação do Parque
     * @return a lista com os lugares de estacionamento
     */
    public ArrayList<ParkingSpace> getParkingSpaces(Park park) {
        ArrayList<ParkingSpace> ps = new ArrayList<>();

        try {
            String sql = "SELECT * FROM ParkingSpace WHERE IdPark = " + park.getIdPark();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) { //Enquanto houver dados
                ps.add(new ParkingSpace(resultSet.getInt(1), resultSet.getBoolean(2),
                        resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return ps;
    }

    /**
     * Adicionar o lugar de estacionamento a um determinado utilizador
     *
     * @param user         é classe que encapsula a informação do Utilizador
     * @param parkingSpace é classe que encapsula a informação do Lugar de Estacionamento
     */
    public void addUserParkingSpace(User user, ParkingSpace parkingSpace, Integer idParkingSpaceOld) {
        try {
            String sql = "SELECT IdPark FROM ParkingSpace WHERE IdParkingSpace = " + parkingSpace.getIdParkingSpace();
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                sql = "UPDATE ParkingSpace SET Reserved = " + true + " WHERE IdParkingSpace = "
                        + parkingSpace.getIdParkingSpace();
                statement.executeUpdate(sql);

                sql = "UPDATE ParkingSpace SET Reserved = " + false + " WHERE IdParkingSpace = "
                        + idParkingSpaceOld;
                statement.executeUpdate(sql);

                sql = "UPDATE User SET IdParkingSpace = " + parkingSpace.getIdParkingSpace() + ", IdPark = "
                        + user.getIdPark() + " WHERE IdUser = " + user.getIdUser()
                        + " AND LicensePlate = '" + user.getLicensePlate() + "'";
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
