package DataBase;

import Models.Park;
import Models.ParkingSpace;
import Models.Request;
import Models.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Diogo Santos e Carolina Rosa
 */
public class DBConnection {

    final String DB_NAME = "smartparking";
    final String USER = "root";
    final String PASS = "123456";
    Connection connection; //Objeto para a ligação com a base de dados
    Statement statement; //String SQL com o script pretendido
    ResultSet resultSet; //Resultado vindo do script da String SQL
    String sql; //String que contêm o script desejado


    /**
     * Construtor da Classe
     * @param ip é o ip do servidor da base de dados
     * @param port é o porto do servidor da base de dados
     */
    public DBConnection(String ip, String port) {
        try {
            System.out.println("Connecting to Database (" + ip + ":" + port + ")");
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + DB_NAME
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
     * @return ArrayList<Park>
     */
    public ArrayList<Park> getParkList() { //Buscar a informação de todos os parques para uma lista
        ArrayList<Park> parks = null;
        try {
            sql = "SELECT * FROM Park";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) { //Se houver dados
                parks = new ArrayList<>();
                while (resultSet.next()) { //Enquanto houver dados
                    parks.add(new Park(resultSet.getInt(1), resultSet.getInt(2),
                            resultSet.getInt(3)));
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return parks;
    }*/

    public ArrayList<Park> getParkList() { //Buscar a informação de todos os parques para uma lista
        ArrayList<Park> parks = null;

        try {
            sql = "SELECT * FROM Park";
            resultSet = statement.executeQuery(sql);
            //Se houver dados

            if (resultSet.next()){
                parks = new ArrayList<>();


            while (resultSet.next()) { //Enquanto houver dados
                int idPark = resultSet.getInt("IdPark");
                int totalParkingSpaces = resultSet.getInt("TotalParkingSpaces");
                int freeParkingSpaces = resultSet.getInt("FreeParkingSpaces");
            }
            }
            resultSet.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return parks;
    }

    /**
     * Lista de todos os Utilizadores registados na BD
     * @return ArrayList<User>
     */
    public ArrayList<User> getUserList() { //Buscar a informação de todos os utilizadores
        ArrayList<User> users = null;
        try {
            sql = "SELECT * FROM User";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) { //Se houver dados
                users = new ArrayList<>();
                while (resultSet.next()) { //Enquanto houver dados
                    users.add(new User(resultSet.getString(1), resultSet.getString(2),
                            resultSet.getDate(3), resultSet.getDate(4),
                            resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7)));
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return users;
    }

    /**
     * Lista de todos os Pedidos registados na BD
     * @return ArrayList<Request>
     */
    public ArrayList<Request> getRequestList() { //Buscar a informação de todos os pedidos
        ArrayList<Request> requests = null;
        try {
            sql = "SELECT * FROM Request";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) { //Se houver dados
                requests = new ArrayList<>();
                while (resultSet.next()) { //Enquanto houver dados
                    requests.add(new Request(resultSet.getInt(1), resultSet.getDate(2),
                            resultSet.getInt(3), resultSet.getInt(4)));
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return requests;
    }

    /**
     * Adicionar utilizador
     * @param users é classe que encapsula a informação a registar na BD
     */
    public void addUser(User users) { //Adicionar utilizador

        sql = "INSERT INTO User(name, licensePlate, entryDate, departureDate, email, idParkingSpace, idPark)"
                + " VALUES(?,?,?,? ,?, ?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, users.getName());
            stmt.setString(2, users.getLicensePlate());
            stmt.setDate(3, users.getEntryData());
            stmt.setDate(4, users.getDepartureData());
            stmt.setString(5, users.getEmail());
            stmt.setInt(6, users.getIdParkingSpace());
            stmt.setInt(7, users.getIdPark());

            stmt.execute();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }


    public void addRequest(Request requests) { //Adicionar Pedido
        sql = "INSERT INTO Request(idRequest,requestDate,state,idUser) VALUES(?,?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, requests.getIdRequest());
            stmt.setDate(2, requests.getRequestDate());
            stmt.setInt(3, requests.getState());
            stmt.setInt(4, requests.getIdUser());

            stmt.execute();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void removeUser(int idUser, String licensePlate) { // remover por utilizador por id ou matricula
        sql = "DELETE FROM User WHERE idUser = ? OR licensePlate= ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idUser);  //correspondente ao parametro que se pretende eliminar
            stmt.setString(2, licensePlate);

            stmt.executeUpdate(); // executa a remoçao

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifyUser(User user) { //TODO modificar dados de utilizador
        sql = "UPDATE User SET licensePlate = ? WHERE IdUser = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, user.getLicensePlate()); //correspondente ao parametro que se pretende alterar
            stmt.setInt(2, user.getIdUser()); //correspondente ao parametro que se pretende alterar

            stmt.executeUpdate(); // executa a modificaçao

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifyRequest(int state) { // modificar o estado do pedido
        sql = "UPDATE Request SET state = ? WHERE state = 0";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, state); //correspondente ao parametro que se pretende alterar

            stmt.executeUpdate(); // executa a modificaçao

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Integer> getFreeParkingSpaces(int parkId) {
        ArrayList<Integer> free = null;

        try {
            sql = "SELECT IdParkingSpace FROM ParkingSpace WHERE IdPark = " + parkId + " AND Reserved = " + false;
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) { //Se houver dados
                free = new ArrayList<>();
                while (resultSet.next()) { //Enquanto houver dados
                    free.add(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return free;
    }

    public ArrayList<ParkingSpace> getParkingSpaces(int parkId) {
        ArrayList<ParkingSpace> ps = null;

        try {
            sql = "SELECT * FROM ParkingSpace WHERE IdPark = " + parkId;
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) { //Se houver dados
                ps = new ArrayList<>();
                while (resultSet.next()) { //Enquanto houver dados
                    ps.add(new ParkingSpace(resultSet.getInt(1), resultSet.getInt(2),
                            resultSet.getInt(3)));
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return ps;
    }

    public void addUserParkingSpace(int idUser, String licensePlate, int idParkingSpace) {
        try {
            sql = "SELECT IdPark FROM ParkingSpace WHERE IdParkingSpace = " + idParkingSpace;
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                sql = "UPDATE ParkingSpace SET Reserved = " + true + " WHERE IdParkingSpace = " + idParkingSpace;
                statement.executeQuery(sql);

                sql = "UPDATE Users SET IdParkingSpace = " + idParkingSpace + ", IdPark = " + resultSet.getInt(1)
                        + " WHERE IdUser = " + idUser + " AND LicensePlate = " + licensePlate;
                statement.executeQuery(sql);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }


}
