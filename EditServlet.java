import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
    private final static String query = "SELECT * FROM user WHERE id=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // Get PrintWriter
        PrintWriter pw = res.getWriter();

        // Set content type
        res.setContentType("text/html");

        // CSS Styling
        pw.print("<style>");
        pw.print("body { background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%); font-family: Arial, sans-serif; color: #fff; display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }");
        pw.print("h2 { text-align: center; font-size: 2rem; margin-bottom: 20px; color: #fff; text-transform: uppercase; letter-spacing: 1.5px; }");
        pw.print("form { background: rgba(255, 255, 255, 0.2); padding: 30px; border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); width: 50%; margin: 0 auto; animation: fadeIn 1s ease-in-out; }");
        pw.print("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }");
        pw.print("td { padding: 10px; font-size: 1.1rem; text-align: left; color: #fff; }");
        pw.print("input[type='text'], input[type='email'], input[type='date'], input[type='password'] { width: 100%; padding: 8px; border-radius: 5px; border: 1px solid #ddd; font-size: 1rem; margin-top: 5px; background: rgba(255, 255, 255, 0.7); color: #333; }");
        pw.print("button { background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%); border: none; padding: 10px 20px; border-radius: 5px; color: #fff; font-weight: bold; cursor: pointer; transition: transform 0.3s ease, background 0.3s ease; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3); margin: 20px 0; width: 48%; }");
        pw.print("button:hover { transform: scale(1.05); background: linear-gradient(135deg, #f5576c 0%, #f093fb 100%); }");
        pw.print("button[type='reset'] { background-color: #ccc; }");
        pw.print("@keyframes fadeIn { from { opacity: 0; transform: translateY(20px); } to { opacity: 1; transform: translateY(0); } }");
        pw.print("</style>");
        
        // Get the ID parameter
        int id = Integer.parseInt(req.getParameter("id"));

        // Load JDBC driver and retrieve user data
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt", "root", "Jyoti@2003");
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            // Generate HTML form with retrieved data
            pw.println("<form action='edit?id=" + id + "' method='post'>");

            pw.println("<table>");

            pw.println("<tr>");
            pw.println("<td>Name</td>");
            pw.println("<td><input type='text' name='name' value='" + rs.getString(2) + "'></td>");
            pw.println("</tr>");

            pw.println("<tr>");
            pw.println("<td>Email</td>");
            pw.println("<td><input type='email' name='email' value='" + rs.getString(3) + "'></td>");
            pw.println("</tr>");

            pw.println("<tr>");
            pw.println("<td>Mobile</td>");
            pw.println("<td><input type='text' name='mobile' value='" + rs.getString(4) + "'></td>");
            pw.println("</tr>");

            pw.println("<tr>");
            pw.println("<td>Date of Birth</td>");
            pw.println("<td><input type='date' name='dob' value='" + rs.getString(5) + "'></td>");
            pw.println("</tr>");

            pw.println("<tr>");
            pw.println("<td>City</td>");
            pw.println("<td><input type='text' name='city' value='" + rs.getString(6) + "'></td>");
            pw.println("</tr>");

            pw.println("<tr>");
            pw.println("<td>Gender</td>");
            pw.println("<td><input type='text' name='gender' value='" + rs.getString(7) + "'></td>");
            pw.println("</tr>");

            pw.println("<tr>");
            pw.println("<td>Password</td>");
            pw.println("<td><input type='password' name='password' value='" + rs.getString(8) + "'></td>");
            pw.println("</tr>");

            pw.println("<tr>");
            pw.println("<td><button type='submit'>Edit</button></td>");
            pw.println("<td><button type='reset'>Cancel</button></td>");
            pw.println("</tr>");

            pw.println("</table>");
            pw.println("</form>");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            pw.print("<h1>Error: " + e.getMessage() + "</h1>");
            e.printStackTrace();
        }

        // Navigation link
        pw.print("<a href='home.html'><button>Home</button></a>");

        // Close the stream
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
