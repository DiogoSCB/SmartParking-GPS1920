package database;

import models.Park;
import models.ParkingSpace;
import models.Request;
import models.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Diogo Santos e Carolina Rosa
 */
public class DBConnection {

    private final String DB_NAME = "smartparking";
    private final String USER = "root";
    private final String PASS = "123456";
    private Connection connection; //Objeto para a ligação com a base de dados
    private Statement statement; //String SQL com o script pretendido
    private ResultSet resultSet; //Resultado vindo do script da String SQL
    private String sql; //String que contêm o script desejado


    /**
     * Construtor da Classe
     *
     * @param ip   é o ip do servidor da base de dados
     * @param port é o porto do servidor da base de dados
     */
    public DBConnection(String ip, String port) {
        try {
            System.out.println("Connecting to Database (" + ip + ":" + port + ")");
            String DB_NAME = "smartparking";
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + DB_NAME
                            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT",
                    USER, PASS);
            System.out.println("Connection Established!");

            statement = connection.createStatement();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * Lista de todos os Parques registados na BD
     *
     * @return ArrayList<Park>
     */
    public ArrayList<Park> getParkList() { //Buscar a informação de todos os parques para uma lista
        ArrayList<Park> parks = new ArrayList<>();
        try {
            sql = "SELECT * FROM Park";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) { //Enquanto houver dados
                parks.add(new Park(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return parks;
    }

    /**
     * Lista de todos os Utilizadores registados na BD
     *
     * @return ArrayList<User>
     */
    public ArrayList<User> getUserList() { //Buscar a informação de todos os utilizadores
        ArrayList<User> users = new ArrayList<>();
        try {
            sql = "SELECT * FROM User";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) { //Enquanto houver dados
                users.add(new User(resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDate(4),
                        resultSet.getDate(5), resultSet.getString(6),
                        resultSet.getInt(7), resultSet.getInt(8)));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return users;
    }

    /**
     * Lista de todos os Pedidos registados na BD
     *
     * @return ArrayList<Request>
     */
    public ArrayList<Request> getRequestList() { //Buscar a informação de todos os pedidos
        ArrayList<Request> requests = new ArrayList<>();
        try {
            sql = "SELECT * FROM Request";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) { //Enquanto houver dados
                requests.add(new Request(resultSet.getInt(1), resultSet.getDate(2),
                        resultSet.getInt(3), resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return requests;
    }

    /**
     * Adicionar utilizador
     *
     * @param users é classe que encapsula a informação a registar na BD
     */
    public void addUser(User users) { //Adicionar utilizador
        sql = "INSERT INTO User(name, licensePlate, entryDate, departureDate, email, idParkingSpace, idPark)"
                + " VALUES(?,?,?,?,?," + "idParkingSpace = " + users.getIdParkingSpace() +"idPark ="  + users.getIdPark() + ")";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, users.getName());
            stmt.setString(2, users.getLicensePlate());
            stmt.setDate(3, users.getEntryDate());
            stmt.setDate(4, users.getDepartureDate());
            stmt.setString(5, users.getEmail());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void removeUser(User user) { //Remover por utilizador
        sql = "DELETE FROM User WHERE idUser = ? AND licensePlate= ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, user.getIdUser());
            stmt.setString(2, user.getLicensePlate());

            stmt.executeUpdate(); // executa a remoçao
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifyUser(User user) {
        sql = "UPDATE User SET name = ?, licensePlate = ?, entryDate = ?, departureDate = ?, email = ?, " +
                "idParkingSpace = " + user.getIdParkingSpace()  + "WHERE IdUser = " + user.getIdUser() + ")";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            //correspondente ao parametro que se pretende alterar
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLicensePlate());
            stmt.setDate(3, user.getEntryDate());
            stmt.setDate(4, user.getDepartureDate());
            stmt.setString(5, user.getEmail());



            stmt.executeUpdate(); // executa a modificaçao
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifyRequest(Request request) { // modificar o estado do pedido
        sql = "UPDATE Request SET state = ? WHERE idRequest = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, request.getState()); //correspondente ao parametro que se pretende alterar
            stmt.setInt(2, request.getIdRequest()); //correspondente ao parametro que se pretende alterar

            stmt.executeUpdate(); // executa a modificaçao
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Integer> getFreeParkingSpaces(Park park) {
        ArrayList<Integer> free = new ArrayList<>();

        try {
            sql = "SELECT IdParkingSpace FROM ParkingSpace WHERE IdPark = " + park.getIdPark() + " AND Reserved = " + false;
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) { //Enquanto houver dados
                free.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return free;
    }

    public ArrayList<ParkingSpace> getParkingSpaces(Park park) {
        ArrayList<ParkingSpace> ps = new ArrayList<>();

        try {
            sql = "SELECT * FROM ParkingSpace WHERE IdPark = " + park.getIdPark();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) { //Enquanto houver dados
                ps.add(new ParkingSpace(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return ps;
    }

    public void addUserParkingSpace(User user, ParkingSpace parkingSpace) {
        try {
            sql = "SELECT IdPark FROM ParkingSpace WHERE IdParkingSpace = " + parkingSpace.getIdParkingSpace();
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                sql = "UPDATE ParkingSpace SET Reserved = " + true + " WHERE IdParkingSpace = " + parkingSpace.getIdParkingSpace();
                statement.executeQuery(sql);

                sql = "UPDATE Users SET IdParkingSpace = " + parkingSpace.getIdParkingSpace() + ", IdPark = "
                        + resultSet.getInt(1) + " WHERE IdUser = " + user.getIdUser()
                        + " AND LicensePlate = " + user.getLicensePlate();
                statement.executeQuery(sql);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
