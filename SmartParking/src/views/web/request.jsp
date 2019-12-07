<%@ page language="java"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="java.sql.*"%>
<%@ page import="java.time.LocalDate"%>

<%
String name = request.getParameter("nome");
String licensePlate = request.getParameter("matricula1")
                    + request.getParameter("matricula2")
                    + request.getParameter("matricula3");
String email = request.getParameter("email");
Integer park = Integer.parseInt(request.getParameter("parque"));

out.println(name + " " + licensePlate + " " + email + " " + park);

String driverName = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/smartparking" +
"?useUnicode=true&useJDBCCompliantTimezoneShift=true" +
"&useLegacyDatetimeCode=false&serverTimezone=GMT";
String dbName = "smartparking";
String user = "root";
String password = "123456";

try {
  Class.forName(driverName);
} catch (ClassNotFoundException e) {
  out.println(e);
}

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null; //Resultado vindo do script da String SQL
try {
  connection = DriverManager.getConnection(connectionUrl, user, password);
  statement = connection.createStatement();
  String sql = null;

  try {
      //Inserir Utilizador
      sql = "INSERT INTO User(name, licensePlate, entryDate, departureDate, " +
      " email, idParkingSpace, idPark) VALUES(" + name + ", " + licensePlate +
      "," + null + "," + null + ", " + email + "," + null + ", " + park + " )";
      statement.executeUpdate(sql);

      //Buscar o idUser do Utilizador inserido
      sql = "SELECT IdUser FROM User WHERE licensePlate = " + licensePlate;
      resultSet = statement.executeQuery(sql);
      Integer idUser = resultSet.getInt(1);

      //Inserir Pedido
      sql = "INSERT INTO Request(requestDate,state,idUser) VALUES(" +
      "2019-12-06" + ", " + 0 + "," + idUser + ")";
      statement.executeUpdate(sql);

  } catch (SQLException u) {
      throw new RuntimeException(u);
  }

} catch (Exception e) {
  out.println(e);
}
%>
