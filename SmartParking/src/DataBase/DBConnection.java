package DataBase;

import Models.Park;

import java.sql.*;
import java.util.ArrayList;

/*

Funções por fazer:
- Buscar informação de todos os pedidos
- Buscar informação de todos os utilizadores.
- Buscar os lugares disponíveis de um determinado parque para o dropdown.
- Modificar o estado de um determinado pedido para Aprovado/Rejeitado.
- Adicionar o utilizador caso tenho sido aprovado(informação vinda de um pedido), com o lugar de estacionamento vazio.
- Adicionar um pedido na tabela.
- Remover utilizador por matricula ou id?.
- Adicionar lugar de estacionamento ao utilizador por matricula ou id?
- Adicionar utilizador (manualmente).
- Modificar dados de um utilizador.

 */

public class DBConnection {

    final String DB_NAME = "SmartParkingDB";
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
                    + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT", USER, PASS);
            System.out.println("Connection Established!");
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
                    parks.add(new Park(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3)));
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return parks;
    }

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection("localhost", "3306");

        //Teste getParkList()
        ArrayList<Park> parks = dbConnection.getParkList();
        for (Park p : parks) {
            System.out.println(p.getIdPark());
            System.out.println(p.getTotalParkingSpace());
            System.out.println(p.getFreeParkingSpace());
            System.out.println();
        }
    }
}
