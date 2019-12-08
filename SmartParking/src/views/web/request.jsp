<jsp:directive.include file = "connection.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
<%
String name = request.getParameter("name");
String licensePlate = request.getParameter("licensePlate1")
                    + request.getParameter("licensePlate2")
                    + request.getParameter("licensePlate3");
String email = request.getParameter("email");
String park = request.getParameter("park");

try {
  connection = DriverManager.getConnection(connectionUrl, user, password);
  statement = connection.createStatement();

  try {
      Integer idPark = 0;
      Integer idUser = 0;
      Integer idParkingSpace = null;
      Date entryDate = null;
      Date departureDate = null;

      //Buscar o IdPark do Parque selecionado
      sql = "SELECT idPark FROM Park WHERE Name = '" + park + "'";
      resultSet = statement.executeQuery(sql);
      if(resultSet.next()){
         idPark = resultSet.getInt(1);
      }

      //Inserir Utilizador
      sql = "INSERT INTO User(name, licensePlate, entryDate, departureDate, email, idParkingSpace, idPark)"
      + " VALUES(?,?,?,?,?," + idParkingSpace + ", " + idPark + " )";
      ps = connection.prepareStatement(sql);
      ps.setString(1, name);
      ps.setString(2, licensePlate);
      ps.setDate(3, entryDate);
      ps.setDate(4, departureDate);
      ps.setString(5, email);
      ps.executeUpdate();
      ps.close();

      //Buscar o idUser do Utilizador inserido
      sql = "SELECT IdUser FROM User WHERE licensePlate = '" + licensePlate + "'";
      resultSet = statement.executeQuery(sql);
      if(resultSet.next()){
         idUser = resultSet.getInt(1);
      }
      //Inserir Pedido
      sql = "INSERT INTO Request(requestDate,state,idUser) VALUES(?, 0," + idUser + ")";
      ps = connection.prepareStatement(sql);
      ps.setDate(1, Date.valueOf(LocalDate.now()));
      ps.executeUpdate();
      ps.close();

  } catch (SQLException u) {
      throw new RuntimeException(u);
  }

} catch (Exception e) {
  out.println(e);
%>


<%
  return;
}
%>
  <meta charset="UTF-8">
  <title>Document</title>
  <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
  <div class="result">
      <h1>O pedido foi enviado ao administrador!</h1>
      <h3>Matrícula do Veículo: <% request.getParameter("licensePlate1"); %>
      -<% request.getParameter("licensePlate2"); %>
      -<% request.getParameter("licensePlate3"); %></h3>
      <h3>Nome do Proprietário: <% request.getParameter("name"); %></h3>
      <h3>Email: <% request.getParameter("email"); %></h3>
      <h3>Parque: <% request.getParameter("park"); %></h3>
      <h3>Será enviado um email brevemente a confirmar o seu pedido e qual o lugar
        de estacionamento que lhe vai ser atribuido</h3>
    </div>
</body>
</html>
