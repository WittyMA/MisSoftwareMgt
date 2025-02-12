

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Servlets") // Servlet Mapping
public class Servlets extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/software_management";
    private static final String JDBC_USER = "yesulikplimits"; // Change if necessary
    private static final String JDBC_PASSWORD = "$WWitty@#MAA2"; // Change if necessary

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String appName = request.getParameter("app_name");
        String versionNumber = request.getParameter("version_number");
        String vendor = request.getParameter("vendor");
        String deploymentDate = request.getParameter("deployment_date");
        String owner = request.getParameter("owner");
        String databaseType = request.getParameter("database_type");
        String operatingSystem = request.getParameter("operating_system");
        String hostingEnvironment = request.getParameter("hosting_environment");
        String purpose = request.getParameter("purpose");
        String criticalRating = request.getParameter("critical_rating");
        String userBase = request.getParameter("user_base");
        String integratedApps = request.getParameter("integrated_apps");
        String authenticationMethod = request.getParameter("authentication_method");
        String dataTypes = request.getParameter("data_types");
        String transactionVolume = request.getParameter("transaction_volume");
        String users = request.getParameter("users");
try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // SQL query with 16 columns matching the form fields
            String sql = "INSERT INTO applications ("
                    + "app_name, version_number, vendor, deployment_date, owner, "
                    + "database_type, operating_system, hosting_environment, purpose, critical_rating, "
                    + "user_base, integrated_apps, authentication_method, data_types, transaction_volume, users"
                    + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, appName);
            stmt.setString(2, versionNumber);
            stmt.setString(3, vendor);
            stmt.setString(4, deploymentDate);
            stmt.setString(5, owner);
            stmt.setString(6, databaseType);
            stmt.setString(7, operatingSystem);
            stmt.setString(8, hostingEnvironment);
            stmt.setString(9, purpose);
            stmt.setString(10, criticalRating);
            stmt.setString(11, userBase);
            stmt.setString(12, integratedApps);
            stmt.setString(13, authenticationMethod);
            stmt.setString(14, dataTypes);
            stmt.setString(15, transactionVolume);
            stmt.setString(16, users);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                response.getWriter().println("<html>"
                        + "<head>"
                        + "<script type=\"text/javascript\" style=\"color:green;\">alert('Application submitted successfully!');"
                        + "window.location.href = 'index.html';"
                        + "</script>"                   
                        + "</head>"
                        + "<body></body>"
                        + "</html>");
            } else {
                response.getWriter().println("<html>"
                        + "<head>"
                        + "<script type=\"text/javascript\">alert('Failed to submitted application!');"
                        + "window.location.href = 'index.html';"
                        + "</script>"
                        + "</head>"
                        + "<body></body>"
                        + "</html>");
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            response.getWriter().println("<html>"
                        + "<head>"
                        + "<script type=\"text/javascript\">alert('Error '" + e.getMessage() + ");"
                         + "window.location.href = 'index.html';"
                        + "</script>"
                        + " window.location.href = 'index.html';"                   
                        + "</head>"
                        + "<body></body>"
                        + "</html>");
        }
    }
}