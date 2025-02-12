package api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet({"/ExportCSVServlet"})
public class ExportCSVServlet extends HttpServlet {
  private static final String JDBC_URL = "jdbc:mysql://localhost:1527/software_management";
  
  private static final String JDBC_USER = "yesulikplimits";
  
  private static final String JDBC_PASSWORD = "$WWitty@#MAA2";
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=applications.csv");
    try {
      PrintWriter out = response.getWriter();
      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:1527/software_management", "yesulikplimits", "$WWitty@#MAA2");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM applications");
        out.println("ID,App Name,Version Number,Vendor,Deployment Date,Owner,Database Type,Operating System,Hosting Environment,Purpose,Critical Rating,User Base,Integrated Apps,Authentication Method,Data Types,Transaction Volume,Users, Created AT");
        while (rs.next())
          out.println("" + rs
              .getInt("id") + "," + rs.getInt("id") + "," + rs
              .getString("app_name") + "," + rs
              .getString("version_number") + "," + rs
              .getString("vendor") + "," + rs
              .getString("deployment_date") + "," + rs
              .getString("owner") + "," + rs
              .getString("database_type") + "," + rs
              .getString("operating_system") + "," + rs
              .getString("hosting_environment") + "," + rs
              .getString("purpose") + "," + rs
              .getString("critical_rating") + "," + rs
              .getString("user_base") + "," + rs
              .getString("integrated_apps") + "," + rs
              .getString("authentication_method") + "," + rs
              .getString("data_types") + "," + rs
              .getString("transaction_volume") + "," + rs
              .getString("users")); 
        rs.close();
        stmt.close();
        conn.close();
        if (out != null)
          out.close(); 
      } catch (Throwable throwable) {
        if (out != null)
          try {
            out.close();
          } catch (Throwable throwable1) {
            throwable.addSuppressed(throwable1);
          }  
        throw throwable;
      } 
    } catch (Exception e) {
      e.printStackTrace();
      response.getWriter().println("Error generating CSV: " + e.getMessage());
    } 
  }
}
