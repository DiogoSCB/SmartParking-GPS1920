<html>
   <head>
      <title>Using GET and POST Method to Read Form Data</title>
   </head>

   <body>
      <center>
      <h1>Using POST Method to Read Form Data</h1>

      <ul>
         <li><p><b>Matricula:</b>
            <%= request.getParameter("matricula1")
            + request.getParameter("matricula2")
            + request.getParameter("matricula3")%>
         </p></li>
         <li><p><b>Nome:</b>
            <%= request.getParameter("nome")%>
         </p></li>
         <li><p><b>Email:</b>
            <%= request.getParameter("email")%>
         </p></li>
         <li><p><b>Parque:</b>
            <%= request.getParameter("parque")%>
         </p></li>
      </ul>

   </body>
</html>
