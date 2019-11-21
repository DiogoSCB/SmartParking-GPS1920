package DataBase;

import java.sql.*;

public class DBConnection {

    final String DB_NAME = "SmartParkingDB";
    final String USER = "root";
    final String PASS = "123456";
    Connection connection; //Objeto para a ligação com a base de dados
    Statement statement; //String SQL com o script pretendido
    ResultSet resultSet; //Resultado vindo do script da String SQL
    String sql; //String que contêm o script desejado

    private DBConnection(String ip, String port) {
        try {
            System.out.println("Connecting to Database (" + ip + ":" + port + ")");
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + DB_NAME
                    + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT", USER, PASS);
            System.out.println("Connection Established!");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /* Exemplo de um pedido a base de dados
    public String CreateUser(String name, String username, String password) { //Regista Utilizador
        try {
            sql = "INSERT INTO users (Name, UserName, PassWord) VALUES ('" + name + "', '" + username + "', '" + password + "')";
            statement.executeUpdate(sql);
            return "SignUpSuccess";
        } catch (SQLException ex) {
            System.err.println(ex);
            return "[DEBUG] ERROR: DataBase Connection";
        }
        return "UsernameAlreadyCreated";
    }*/

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection("localhost", "3306");
    }
}
