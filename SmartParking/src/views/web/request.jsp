<jsp:directive.include file = "connection.jsp" />

<%
String name = request.getParameter("name");
String licensePlate = request.getParameter("licensePlate1")
                    + request.getParameter("licensePlate2")
                    + request.getParameter("licensePlate3");
String email = request.getParameter("email");
String park = request.getParameter("park");

out.println(name + " " + licensePlate + " " + email + " " + park);

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
  return;
}

out.println("O pedido foi enviado ao administrador!");
%>
