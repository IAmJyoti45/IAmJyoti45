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

@WebServlet("/edit")
public class editRecordServalet extends HttpServlet {
    private final static String query = "UPDATE user SET name=?, email=?, mobile=?, dob=?, city=?, gender=?, password=? WHERE id=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        // Get PrintWriter
        PrintWriter pw = res.getWriter();
        
        // Set content type
        res.setContentType("text/html");

        // CSS Styling
        pw.print("<style>");
        pw.print("body { background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%); font-family: Arial, sans-serif; color: #fff; display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }");
        pw.print(".container { width: 100%; max-width: 500px; margin-top: 50px; background: rgba(255, 255, 255, 0.2); padding: 30px; border-radius: 10px; box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3); }");
        pw.print("h2 { text-align: center; color: #fff; font-size: 2rem; margin-bottom: 20px; text-transform: uppercase; letter-spacing: 1px; }");
        pw.print("input[type='text'], input[type='email'], input[type='date'], input[type='password'], select { width: 100%; padding: 12px; margin: 10px 0; border-radius: 5px; border: none; font-size: 1rem; }");
        pw.print("button { background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%); border: none; padding: 10px 20px; border-radius: 5px; color: #fff; font-weight: bold; cursor: pointer; transition: transform 0.3s ease, background 0.3s ease; width: 100%; }");
        pw.print("button:hover { transform: scale(1.05); background: linear-gradient(135deg, #f5576c 0%, #f093fb 100%); }");
        pw.print(".form-group { margin-bottom: 20px; }");
        pw.print(".message { text-align: center; font-size: 1.2rem; padding: 10px; margin-top: 20px; color: #fff; background: #4caf50; border-radius: 5px; }");
        pw.print("</style>");

        // Get values
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String dob = req.getParameter("dob");
        String city = req.getParameter("city");
        String gender = req.getParameter("gender");
        String password = req.getParameter("password");

        // Load the JDBC driver and update the record
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt", "root", "Jyoti@2003");
            PreparedStatement ps = con.prepareStatement(query);

            // Set the values
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, dob);
            ps.setString(5, city);
            ps.setString(6, gender);
            ps.setString(7, password);
            ps.setInt(8, id);

            int count = ps.executeUpdate();

            pw.print("<div class='container'>");
            pw.print("<h2>Edit User Record</h2>");
            if (count == 1) {
                pw.print("<div class='message'>Record Edited Successfully</div>");
            } else {
                pw.print("<div class='message'>Record Not Edited</div>");
            }
            pw.print("<a href='home.html'><button>Home</button></a>");
            pw.print("<a href='showdata'><button>Show User</button></a>");
            pw.print("</div>");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            pw.print("<div class='message'>Error: " + e.getMessage() + "</div>");
            e.printStackTrace();
        }

        // Close the stream
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
