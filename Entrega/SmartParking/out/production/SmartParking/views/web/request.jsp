<!DOCTYPE html>
<html lang="pt-PT">
<head>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<jsp:directive.include file = "connection.jsp" />
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

  } catch (SQLException e) {
      throw e;
  }

} catch (SQLIntegrityConstraintViolationException e) {
%>
  <h1>A Matrícula inserida já se encontra registada!</h1>
  <div class="result box">
    <h3>Matrícula do Veículo: <% out.println(licensePlate.substring(0, 2) + "-"
      + licensePlate.substring(2, 4) + "-"
      + licensePlate.substring(4, 6)); %></h3>
  </div>
</body>
</html>
<%
  return;
} catch (Exception e) {
%>
  <div class="result box">
    <h2>Ocorreu um erro ao tentar enviar o pedido ao administrador! Tente mais tarde.</h2>
  </div>
</body>
</html>
<%
  return;
}
%>
  <h1>O pedido foi enviado ao Administrador!</h1>
  <div class="result box">
    <h3>Matrícula do Veículo:
    <% out.println(licensePlate.substring(0, 2) + "-"
      + licensePlate.substring(2, 4) + "-"
      + licensePlate.substring(4, 6)); %></h3>
    <h3>Nome do Proprietário: <% out.println(name); %></h3>
    <h3>Email: <% out.println(email); %></h3>
    <h3>Parque: <% out.println(park); %></h3>
  </div>
  <h3>Será enviado um email brevemente a confirmar o seu pedido e qual o lugar
    de estacionamento que lhe vai ser atribuído.</h3>
</body>
</html>
