package api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.json.JSONObject;

@WebServlet({"/ApplicationServlet"})
public class ApplicationServlet extends HttpServlet {
  private static final String JDBC_URL = "jdbc:mysql://localhost:1527/software_management";
  
  private static final String JDBC_USER = "yesulikplimits";
  
  private static final String JDBC_PASSWORD = "$WWitty@#MAA2";
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String appName = (request.getParameter("app_name") != null) ? request.getParameter("app_name").trim() : "";
    String versionNumber = (request.getParameter("version_number") != null) ? request.getParameter("version_number").trim() : "";
    String vendor = (request.getParameter("vendor") != null) ? request.getParameter("vendor").trim() : "";
    String deploymentDate = (request.getParameter("deployment_date") != null) ? request.getParameter("deployment_date").trim() : "";
    String owner = (request.getParameter("owner") != null) ? request.getParameter("owner").trim() : "";
    String databaseType = (request.getParameter("database_type") != null) ? request.getParameter("database_type").trim() : "";
    String operatingSystem = (request.getParameter("operating_system") != null) ? request.getParameter("operating_system").trim() : "";
    String hostingEnvironment = (request.getParameter("hosting_environment") != null) ? request.getParameter("hosting_environment").trim() : "";
    String purpose = (request.getParameter("purpose") != null) ? request.getParameter("purpose").trim() : "";
    String criticalRating = (request.getParameter("critical_rating") != null) ? request.getParameter("critical_rating").trim() : "";
    String userBase = (request.getParameter("user_base") != null) ? request.getParameter("user_base").trim() : "";
    String integratedApps = (request.getParameter("integrated_apps") != null) ? request.getParameter("integrated_apps").trim() : "";
    String authenticationMethod = (request.getParameter("authentication_method") != null) ? request.getParameter("authentication_method").trim() : "";
    String dataTypes = (request.getParameter("data_types") != null) ? request.getParameter("data_types").trim() : "";
    String transactionVolume = (request.getParameter("transaction_volume") != null) ? request.getParameter("transaction_volume").trim() : "";
    String users = (request.getParameter("users") != null) ? request.getParameter("users").trim() : "";
    if (appName.isEmpty()) {
      sendErrorResponse(response, "Application name cannot be empty.");
      return;
    } 
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/software_management", "yesulikplimits", "$WWitty@#MAA2");
      try {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO applications (app_name, version_number, vendor, deployment_date, owner, database_type, operating_system, hosting_environment, purpose, critical_rating, user_base, integrated_apps, authentication_method, data_types, transaction_volume, users) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        try {
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
            sendSuccessResponse(response, "Application submitted successfully!");
          } else {
            sendErrorResponse(response, "Failed to submit application.");
          } 
          if (stmt != null)
            stmt.close(); 
        } catch (Throwable throwable) {
          if (stmt != null)
            try {
              stmt.close();
            } catch (Throwable throwable1) {
              throwable.addSuppressed(throwable1);
            }  
          throw throwable;
        } 
        if (conn != null)
          conn.close(); 
      } catch (Throwable throwable) {
        if (conn != null)
          try {
            conn.close();
          } catch (Throwable throwable1) {
            throwable.addSuppressed(throwable1);
          }  
        throw throwable;
      } 
    } catch (ClassNotFoundException e) {
      sendErrorResponse(response, "Database driver not found.");
    } catch (SQLException e) {
      sendErrorResponse(response, "Database error: " + e.getMessage());
    } 
  }
  
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
    try {
      BufferedReader reader = request.getReader();
      StringBuilder sb = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null)
        sb.append(line); 
      JSONObject json = new JSONObject(sb.toString());
      int id = json.getInt("id");
      String appName = json.getString("app_name");
      String versionNumber = json.getString("version_number");
      String vendor = json.getString("vendor");
      String deploymentDate = json.getString("deployment_date");
      String owner = json.getString("ower");
      String databaseType = json.getString("database_type");
      String operatingSystem = json.getString("operating_system");
      String hostingEnvironment = json.getString("hosting_environment");
      String purpose = json.getString("deployment_date");
      String criticalRating = json.getString("critical_rating");
      String userBase = json.getString("user_base");
      String integratedApps = json.getString("integrated_apps");
      String authenticationMethod = json.getString("authentication_method");
      String dataTypes = json.getString("data_types");
      String transactionVolume = json.getString("transaction_volume");
      String users = json.getString("users");
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/software_management", "yesulikplimits", "$WWitty@#MAA2");
      String sql1 = "UPDATE applications SET app_name=?, version_number=?, vendor=?, deployment_date=? owner=?, database_type=?, operating_system=?, hosting_environment=?, purpose=?, critical_rating=?, user_base=?, integrated_apps=?, authentication_method=?, data_types=?, transaction_volume=?, users=?WHERE id=?";
      PreparedStatement stmt = conn.prepareStatement(sql1);
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
      stmt.setInt(17, id);
      int rowsUpdated = stmt.executeUpdate();
      if (rowsUpdated > 0) {
        response.getWriter().write("Application updated successfully!");
      } else {
        response.sendError(404, "Application not found.");
      } 
    } catch (Exception e) {
      response.sendError(500, "Database error: " + e.getMessage());
    } 
  }
  
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String appId = request.getParameter("id");
    if (appId == null || appId.isEmpty()) {
      response.setStatus(400);
      response.getWriter().write("Error: Application ID is required for deletion.");
      return;
    } 
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/software_management", "yesulikplimits", "$WWitty@#MAA2");
      String sql = "DELETE FROM applications WHERE id = ?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, Integer.parseInt(appId));
      int rowsDeleted = stmt.executeUpdate();
      stmt.close();
      conn.close();
      if (rowsDeleted > 0) {
        response.getWriter().write("Application deleted successfully.");
      } else {
        response.setStatus(404);
        response.getWriter().write("Error: Application not found.");
      } 
    } catch (Exception e) {
      response.setStatus(500);
      response.getWriter().write("Error: " + e.getMessage());
    } 
  }
  
  private void sendSuccessResponse(HttpServletResponse response, String message) throws IOException {
    response.getWriter().println("<html><head><script type=\"text/javascript\">alert('" + message + "');window.location.href = 'crud.html';</script></head><body></body></html>");
  }
  
  private void sendErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
    response.getWriter().println("<html><head><script type=\"text/javascript\">alert('Error: " + errorMessage
        
        .replace("'", "\\'") + "');window.location.href = 'crud.html';</script></head><body></body></html>");
  }
}
