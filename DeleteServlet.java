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

@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {

    private final static String delete_query = "DELETE FROM user WHERE id=?";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        // Get PrintWriter
        PrintWriter pw = res.getWriter();
        
        // Set content type
        res.setContentType("text/html");
     // CSS Styling
        pw.print("<style>");
        pw.print("body { background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%); font-family: Arial, sans-serif; color: #fff; display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }");
        pw.print("h3 { text-align: center; font-size: 2rem; margin-bottom: 20px; color: #fff; text-transform: uppercase; letter-spacing: 1.5px; }");
        pw.print("div { text-align: center; padding: 20px; background: rgba(255, 255, 255, 0.2); border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); animation: fadeIn 1s ease-in-out; }");
        pw.print("button { background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%); border: none; padding: 10px 20px; border-radius: 5px; color: #fff; font-weight: bold; cursor: pointer; transition: transform 0.3s ease, background 0.3s ease; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3); margin: 20px 0; }");
        pw.print("button:hover { transform: scale(1.05); background: linear-gradient(135deg, #f5576c 0%, #f093fb 100%); }");
        pw.print("@keyframes fadeIn { from { opacity: 0; transform: translateY(20px); } to { opacity: 1; transform: translateY(0); } }");
        pw.print("</style>");

        
        // Get the 'id' parameter
        int id = Integer.parseInt(req.getParameter("id"));

        // Load JDBC driver and perform delete operation
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermgmt", "root", "Jyoti@2003");
            PreparedStatement ps = con.prepareStatement(delete_query);

            // Set the ID in the query
            ps.setInt(1, id);

            int count = ps.executeUpdate();

            // Display the result of the delete operation
            pw.print("<div>");
            if (count == 1) {
                pw.print("<h3>Record deleted successfully.</h3>");
            } else {
                pw.print("<h3>Record not deleted.</h3>");
            }
            pw.print("</div>");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            pw.print("<h3>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace();
        }

        // Links for navigation
        pw.print("<a href='home.html'><button>Home</button></a>");
        pw.print("&nbsp;&nbsp;");
        pw.print("<a href='showdata'><button>Show User</button></a>");

        // Close the stream
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
