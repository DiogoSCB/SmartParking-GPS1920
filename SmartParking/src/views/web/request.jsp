<%@ page language="java"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="java.sql.*"%>

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
String userId = "root";
String password = "123456";

try {
  Class.forName(driverName);
} catch (ClassNotFoundException e) {
  out.println(e);
}

Connection connection = null;
Statement statement = null;
try {
  connection = DriverManager.getConnection(connectionUrl, userId, password);

  String sql = "INSERT INTO User(name, licensePlate, entryDate, departureDate, email," +
  " idParkingSpace, idPark) VALUES(?,?," + null + "," + null + ",?," + null
  + ", " + park + " )";

  try {
      PreparedStatement stmt = connection.prepareStatement(sql);

      stmt.setString(1, name);
      stmt.setString(2, licensePlate);
      stmt.setString(3, email);

      stmt.executeUpdate();
      stmt.close();
  } catch (SQLException u) {
      throw new RuntimeException(u);
  }

} catch (Exception e) {
  out.println(e);
}
%>
