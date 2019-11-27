package DataBase;

import Models.Park;
import Models.Request;
import Models.User;

import java.sql.*;
import java.util.ArrayList;

/*

Funções por fazer:
- Buscar informação de todos os pedidos -->check
- Buscar informação de todos os utilizadores.-->check
- Buscar os lugares disponíveis de um determiado parque para o dropdown. -->check
- Modificar o estado de um determinado pedido para Aprovado/Rejeitado. -->check
- Adicionar um pedido na tabela. -->check
- Remover utilizador por matricula ou id?.-->check
- Adicionar lugar de estacionamento ao utilizador por matricula ou id? -->check
- Adicionar utilizador (manualmente). -->check
- Modificar dados de um utilizador. --> check

 */

public class DBConnection {

    final String DB_NAME = "smartparking";
    final String USER = "root";
    final String PASS = "123456";
    Connection connection; //Objeto para a ligação com a base de dados
    Statement statement; //String SQL com o script pretendido
    ResultSet resultSet; //Resultado vindo do script da String SQL
    String sql; //String que contêm o script desejado

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
    }

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
                            resultSet.getString(5), resultSet.getInt(6)));
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return users;
    }

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

    public void addUser(User users) { //Adicionar utilizador
        sql = "INSERT INTO User(name, licensePlate, entryDate, departureDate, email, IdParkingSpace, IdPark)"
                + " VALUES(?,?,?,?,?, null,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, users.getName());
            stmt.setString(2, users.getLicensePlate());
            stmt.setDate(3, users.getEntryData());
            stmt.setDate(4, users.getDepartureData());
            stmt.setString(5, users.getEmail());
            stmt.setInt(6, users.getIdPark());

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

            stmt.setInt(2, idUser);  //correspondente ao parametro que se pretende eliminar
            stmt.setString(3, licensePlate);

            stmt.executeUpdate(); // executa a remoçao

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifyUser(User user) { //TODO modificar dados de utilizador
        sql = "UPDATE User SET licensePlate = ? WHERE IdUser = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(3, user.getLicensePlate()); //correspondente ao parametro que se pretende alterar

            stmt.executeUpdate(); // executa a modificaçao

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifyRequest(int state) { // modificar o estado do pedido
        sql = "UPDATE Request SET state = 'Aprovado' OR state ='Rejeitado' WHERE state = 'Pendente'";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(3, state); //correspondente ao parametro que se pretende alterar

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

    public void addUserParkingSpace(int idUser, String licensePlate, int idParkingSpace) {
        try {
            sql = "SELECT IdPark FROM ParkingSpace WHERE IdParkingSpace = " + idParkingSpace;
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                sql = "UPDATE ParkingSpace SET Reserved = " + true + " WHERE IdParkingSpace = " + idParkingSpace;
                statement.executeQuery(sql);

                sql = "UPDATE Users SET IdParkingSpace = " + idParkingSpace +", IdPark = " + resultSet.getInt(1)
                        + " WHERE IdUser = " + idUser + " AND LicensePlate = " + licensePlate;
                statement.executeQuery(sql);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection("localhost", "3306");

        //dbConnection.addUser(new User("Diogo Branco", "69DB44", null, null, "diogo@gmail.com", 0));

        //Teste getParkList()
        ArrayList<Park> parks = dbConnection.getParkList();
        for (Park p : parks) {
            System.out.print(p.getIdPark() + " ");
            System.out.print(p.getTotalParkingSpace() + " ");
            System.out.print(p.getFreeParkingSpace() + " ");
            System.out.println();
        }
        //Teste getUserList()
        /*ArrayList<User> users = dbConnection.getUserList();
        for (User p : users) {
            System.out.print(p.getIdUser() + " ");
            System.out.print(p.getLicensePlate() + " ");
            System.out.print(p.getEntryData() + " ");
            System.out.print(p.getDepartureData() + " ");
            System.out.print(p.getEmail() + " ");
            System.out.print(p.getPark() + " ");
            System.out.println();
        }
        //Teste getRequestList()
        ArrayList<Request> requests = dbConnection.getRequestList();
        for (Request p : requests) {
            System.out.print(p.getIdRequest() + " ");
            System.out.print(p.getRequestDate() + " ");
            System.out.print(p.getState() + " ");
            System.out.print(p.getIdUser() + " ");
            System.out.println();
        }*/
    }
}