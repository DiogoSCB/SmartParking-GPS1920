<%@ page language="java"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="java.sql.*"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.time.LocalDate"%>

<%
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
PreparedStatement ps = null;
Statement statement = null;
ResultSet resultSet = null;
String sql = null;
%>
