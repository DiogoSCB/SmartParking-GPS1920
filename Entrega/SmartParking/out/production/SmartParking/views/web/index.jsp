<!DOCTYPE html>
<html lang="pt-PT">
<head>
  <title>SmartParking</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <link rel="stylesheet" href="css/style.css"/>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/form-validation.js" type="text/javascript"></script>
  <script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/additional-methods.js"></script>
</head>
<jsp:directive.include file = "connection.jsp" />
<%
  ArrayList<String> parks = new ArrayList<>();

  try {
    connection = DriverManager.getConnection(connectionUrl, user, password);
    statement = connection.createStatement();

    try {
        //Buscar a lista de Id's dos Parques
        sql = "SELECT Name FROM Park";
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) { //Enquanto houver dados
          parks.add(resultSet.getString(1));
        }
    } catch (SQLException u) {
        throw new RuntimeException(u);
    }

  } catch (Exception e) {
    out.println(e);
  }
%>
<body>
    <div class="container">
      <h1>Efetuar Pedido</h1>
        <form action="request.jsp" method="post" name="request">
          <label for="licensePlate">Matrícula</label>
          <table>
          <tbody>
            <tr>
              <td class="licensePlate">
                <input type="text" name="licensePlate1" id="licensePlate1" placeholder="00" minlength="2" maxlength="2"/>
              </td>
              <td class="licensePlate">
                  <input type="text" name="licensePlate2" id="licensePlate2" placeholder="AA" minlength="2" maxlength="2"/>
              </td>
              <td class="licensePlate">
                  <input type="text" name="licensePlate3" id="licensePlate3" placeholder="00" minlength="2" maxlength="2"/>
              </td>
            </tr>
          </tbody>
          </table>

          <label for="name">Nome do Proprietário</label>
          <input type="text" name="name" id="name" placeholder="Nome" minlength="3" maxlength="40"/>

          <label for="email">Email</label>
          <input type="email" name="email" id="email" placeholder="exemplo@email.com"/>

          <label for="park">Parque</label>
          <select name="park" id="park">
            <% for (String name : parks) { %>
              <option value="<%= name %>"><%= name %></option>
            <%}%>
          </select>

          <input type="submit" name="submit" id="submit" value="Enviar" onclick ="submetendo_formulario(this.form)" class="button">

        </form>
      </div>
</body>
</html>
