package Servlets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(urlPatterns = "/home")
public class HomeServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Html code with styling + table for the studentpage
        String top = "<head><title>Stundenter</title></head>"
                    + "<body style = 'background-color: #cccec9;'>"
                    +"<h1 style = 'text-align: center;'>Studenter</h1>"
                    + "<table style = 'margin-left: auto; margin-right: auto; border: 1px solid black; background-color: #57864b;'>"
                    + "<tr><th>id</th><th>Namn</th><th>Efternamn</th><th>Ort</th><th>intressen</th></tr>";

        String bot = "</table>"
                    + "<div style = 'text-align: center;'>"
                    + "<a href=\"http://localhost:9090\"> Hem </a>"
                    + "<a href=\"http://localhost:9090/courses\"> Kurser </a>"
                    + "<a href=\"http://localhost:9090/school\"> Närvaro </a>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

        try(PrintWriter out = resp.getWriter()) {
            out.println(top);

            try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //PORT and DbName should be changed

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gritacademy",
                    "sami", "");
            Statement stmt = con.createStatement();
            //TABLENAME should be changed
            ResultSet rs = stmt.executeQuery("select * from studenter;");

            //putting data into the table
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt(1) + "</td>");
                    out.println("<td>" + rs.getString(2) + "</td>");
                    out.println("<td>" + rs.getString(3) + "</td>");
                    out.println("<td>" + rs.getString(4) + "</td>");
                    out.println("<td>" + rs.getString(5) + "</td>");
                    out.println("</tr>");

            }
            con.close();
            out.println(bot);

            } catch (Exception e) {
                System.out.println(e);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

