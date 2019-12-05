import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WebServer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("matricula1") + req.getParameter("matricula2")
                + req.getParameter("matricula3"));
        System.out.println(req.getParameter("nome"));
        System.out.println(req.getParameter("email"));
        System.out.println(req.getParameter("parque"));

        PrintWriter writer = resp.getWriter();

        String string = "O pedido foi enviado ao administrador";
        writer.println(string);
    }
}
