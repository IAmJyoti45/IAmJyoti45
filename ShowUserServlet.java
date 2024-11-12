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

@WebServlet("/showdata")
public class ShowUserServlet extends HttpServlet {

    private final static String query = "SELECT * FROM user";

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
        pw.print("table { width: 80%; border-collapse: collapse; background: rgba(255, 255, 255, 0.2); border-radius: 10px; overflow: hidden; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); margin: 20px auto; animation: fadeIn 1s ease-in-out; }");
        pw.print("th, td { padding: 12px 15px; text-align: center; color: #fff; font-size: 0.95rem; }");
        pw.print("th { background: rgba(0, 0, 0, 0.4); text-transform: uppercase; font-weight: bold; border-bottom: 2px solid #2575fc; }");
        pw.print("tr:nth-child(even) { background: rgba(0, 0, 0, 0.2); }");
        pw.print("tr:hover { background: rgba(255, 255, 255, 0.1); transition: background 0.3s ease; }");
        
        // Edit/Delete Link Styling
        pw.print(".action-btn { padding: 6px 12px; color: #fff; text-decoration: none; border-radius: 5px; font-weight: bold; transition: transform 0.2s ease, background-color 0.3s ease; }");
        pw.print(".edit-btn { background-color: #4CAF50; }");
        pw.print(".delete-btn { background-color: #f44336; }");
        pw.print(".action-btn:hover { transform: scale(1.1); }");
        pw.print(".edit-btn:hover { background-color: #45a049; }");
        pw.print(".delete-btn:hover { background-color: #d32f2f; }");

        // Button styling
        pw.print("button { background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%); border: none; padding: 10px 20px; border-radius: 5px; color: #fff; font-weight: bold; cursor: pointer; transition: transform 0.3s ease, background 0.3s ease; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3); margin: 20px 0; }");
        pw.print("button:hover { transform: scale(1.05); background: linear-gradient(135deg, #f5576c 0%, #f093fb 100%); }");

        // Fade-in animation
        pw.print("@keyframes fadeIn { from { opacity: 0; transform: translateY(20px); } to { opacity: 1; transform: translateY(0); } }");
        pw.print("</style>");

        // Page title
        pw.print("<h2>User Data</h2>");
        
        // Start of table
        pw.print("<table>");

        // Table header
        pw.print("<tr>");
        pw.print("<th>ID</th>");
        pw.print("<th>Name</th>");
        pw.print("<th>Email</th>");
        pw.print("<th>Mobile</th>");
        pw.print("<th>DOB</th>");
        pw.print("<th>City</th>");
        pw.print("<th>Gender</th>");
        pw.print("<th>Password</th>");
        pw.print("<th>Edit</th>");
        pw.print("<th>Delete</th>");
        pw.print("</tr>");

        // Load the JDBC driver and fetch data
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt", "root", "Jyoti@2003");
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery(); 
            
            // Populate table with data
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String mobile = rs.getString(4);
                String dob = rs.getString(5);
                String city = rs.getString(6);
                String gender = rs.getString(7);
                String password = rs.getString(8);

                pw.println("<tr>");
                pw.println("<td>" + id + "</td>");
                pw.println("<td>" + name + "</td>");
                pw.println("<td>" + email + "</td>");
                pw.println("<td>" + mobile + "</td>");
                pw.println("<td>" + dob + "</td>");
                pw.println("<td>" + city + "</td>");
                pw.println("<td>" + gender + "</td>");
                pw.println("<td>" + password + "</td>");
                
                // Edit and delete links with styled buttons
                pw.println("<td><a href='editurl?id=" + id + "' class='action-btn edit-btn'>Edit</a></td>");
                pw.println("<td><a href='deleteurl?id=" + id + "' class='action-btn delete-btn'>Delete</a></td>");
                pw.println("</tr>");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            pw.print("<h1>Error: " + e.getMessage() + "</h1>");
            e.printStackTrace();
        }

        // Link back to home page
        pw.print("</table>");
        pw.print("<a href='home.html'><button>Home</button></a>");
        
        // Close the stream
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
