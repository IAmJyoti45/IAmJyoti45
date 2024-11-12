import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final static String query = "insert into user (name,email,mobile,dob,city,gender,password) values(?,?,?,?,?,?,?)";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        // Get PrintWriter
        PrintWriter pw = res.getWriter();
        
        // Set content type
        res.setContentType("text/html");
     // CSS Styling for the registration page
        pw.print("<style>");
        pw.print("body {");
        pw.print("  background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%);");
        pw.print("  font-family: Arial, sans-serif;");
        pw.print("  color: #fff;");
        pw.print("  display: flex;");
        pw.print("  justify-content: center;");
        pw.print("  align-items: center;");
        pw.print("  min-height: 100vh;");
        pw.print("  margin: 0;");
        pw.print("}");

        pw.print("h1 {");
        pw.print("  text-align: center;");
        pw.print("  font-size: 2rem;");
        pw.print("  margin-bottom: 20px;");
        pw.print("  color: #fff;");
        pw.print("  text-transform: uppercase;");
        pw.print("  letter-spacing: 1.5px;");
        pw.print("}");

        pw.print("div {");
        pw.print("  background: rgba(0, 0, 0, 0.5);");
        pw.print("  border-radius: 8px;");
        pw.print("  padding: 20px;");
        pw.print("  width: 400px;");
        pw.print("  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);");
        pw.print("  margin: 20px auto;");
        pw.print("  text-align: center;");
        pw.print("}");

        pw.print("input, select {");
        pw.print("  width: 100%;");
        pw.print("  padding: 10px;");
        pw.print("  margin: 10px 0;");
        pw.print("  border: 2px solid #fff;");
        pw.print("  border-radius: 5px;");
        pw.print("  background-color: rgba(255, 255, 255, 0.2);");
        pw.print("  color: #fff;");
        pw.print("  font-size: 1rem;");
        pw.print("}");

        pw.print("button {");
        pw.print("  background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);");
        pw.print("  border: none;");
        pw.print("  padding: 10px 20px;");
        pw.print("  border-radius: 5px;");
        pw.print("  color: #fff;");
        pw.print("  font-weight: bold;");
        pw.print("  cursor: pointer;");
        pw.print("  transition: transform 0.3s ease, background 0.3s ease;");
        pw.print("  margin: 20px 0;");
        pw.print("}");

        pw.print("button:hover {");
        pw.print("  transform: scale(1.05);");
        pw.print("  background: linear-gradient(135deg, #f5576c 0%, #f093fb 100%);");
        pw.print("}");

        pw.print("</style>");


        // Get form values
        String name = req.getParameter("userName");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String dob = req.getParameter("dob");
        String city = req.getParameter("city");
        String gender = req.getParameter("gender");

        // Load JDBC driver and execute database query
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt", "root", "Jyoti@2003");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, dob);
            ps.setString(5, city);
            ps.setString(6, gender);
            ps.setString(7, password);
            
            int count = ps.executeUpdate();
            
            // Output result as plain HTML
            pw.print("<div>");
            if (count == 1) {
                pw.print("<h1>Record registered successfully</h1>");
            } else {
                pw.print("<h1>Record registration unsuccessful</h1>");
            }

            // Close resources
            ps.close();
            con.close();

        } catch (ClassNotFoundException e) {
            pw.print("<h1>Error: " + e.getMessage() + "</h1>");
            e.printStackTrace();
        } catch (SQLException e) {
            pw.print("<h1>Error: " + e.getMessage() + "</h1>");
            e.printStackTrace();
        }

        // Link back to home page
        pw.print("<a href='home.html'><button>Home</button></a>");
        pw.print("</div>");

        // Close the stream
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
