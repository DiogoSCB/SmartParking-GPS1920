<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="css/style.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
	<script src="js/form-validation.js"></script>
	<script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/additional-methods.js"></script>
</head>
<body>
    <div class="container">
        <h2>Pedido</h2>
        <form action="WebServer" method="post">

          <label for="matricula">Matricula</label>
          <table>
            <tr>
              <td>
                <input type="text" name="matricula1" id="matricula1" placeholder="00"/>
              </td>
              <td>
                  <input type="text" name="matricula2" id="matricula2" placeholder="AA"  />
              </td>
              <td>
                  <input type="text" name="matricula3" id="matricula3" placeholder="00"/>
              </td>
            </tr>
          </table>

          <label for="nome">Nome do Proprietário</label>
          <input type="text" name="nome" id="nome" placeholder="António Cruz"/>

          <label for="email">Email</label>
          <input type="email" name="email" id="email" placeholder="exemplo@mail.pt"/>

          <label for="parque">Parque</label>
          <select name="parque" id="parque">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
          </select>

          <button type="submit">Register</button>

        </form>
      </div>
</body>
</html>
