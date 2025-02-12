package api;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet({"/ExportExcelServlet"})
public class ExportExcelServlet extends HttpServlet {
  private static final String JDBC_URL = "jdbc:mysql://localhost:3306/software_management";
  
  private static final String JDBC_USER = "yesulikplimits";
  
  private static final String JDBC_PASSWORD = "$WWitty@#MAA2";
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    response.setHeader("Content-Disposition", "attachment; filename=applications.xlsx");
    try {
      XSSFWorkbook xSSFWorkbook = new XSSFWorkbook();
      try {
        ServletOutputStream servletOutputStream = response.getOutputStream();
        try {
          Sheet sheet = xSSFWorkbook.createSheet("Applications");
          Row headerRow = sheet.createRow(0);
          String[] headers = { 
              "ID", "App Name", "Version Number", "Vendor", "Deployment Date", "Owner", "Database Type", "Operating System", "Hosting Environment", "Purpose", 
              "Critical Rating", "User Base", "Integrated Apps", "Authentication Method", "Data Types", "Transaction Volume", "Users", "Created At" };
          CellStyle headerStyle = xSSFWorkbook.createCellStyle();
          Font font = xSSFWorkbook.createFont();
          font.setBold(true);
          headerStyle.setFont(font);
          for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
          } 
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/software_management", "yesulikplimits", "$WWitty@#MAA2");
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery("SELECT * FROM applications");
          int rowIndex = 1;
          while (rs.next()) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(rs.getInt("id"));
            row.createCell(1).setCellValue(rs.getString("app_name"));
            row.createCell(2).setCellValue(rs.getString("version_number"));
            row.createCell(3).setCellValue(rs.getString("vendor"));
            row.createCell(4).setCellValue(rs.getString("deployment_date"));
            row.createCell(5).setCellValue(rs.getString("owner"));
            row.createCell(6).setCellValue(rs.getString("database_type"));
            row.createCell(7).setCellValue(rs.getString("operating_system"));
            row.createCell(8).setCellValue(rs.getString("hosting_environment"));
            row.createCell(9).setCellValue(rs.getString("purpose"));
            row.createCell(10).setCellValue(rs.getString("critical_rating"));
            row.createCell(11).setCellValue(rs.getString("user_base"));
            row.createCell(12).setCellValue(rs.getString("integrated_apps"));
            row.createCell(13).setCellValue(rs.getString("authentication_method"));
            row.createCell(14).setCellValue(rs.getString("data_types"));
            row.createCell(15).setCellValue(rs.getString("transaction_volume"));
            row.createCell(16).setCellValue(rs.getString("users"));
            row.createCell(17).setCellValue(rs.getString("created_at"));
          } 
          for (int j = 0; j < headers.length; j++)
            sheet.autoSizeColumn(j); 
          xSSFWorkbook.write((OutputStream)servletOutputStream);
          rs.close();
          stmt.close();
          conn.close();
          if (servletOutputStream != null)
            servletOutputStream.close(); 
        } catch (Throwable throwable) {
          if (servletOutputStream != null)
            try {
              servletOutputStream.close();
            } catch (Throwable throwable1) {
              throwable.addSuppressed(throwable1);
            }  
          throw throwable;
        } 
        xSSFWorkbook.close();
      } catch (Throwable throwable) {
        try {
          xSSFWorkbook.close();
        } catch (Throwable throwable1) {
          throwable.addSuppressed(throwable1);
        } 
        throw throwable;
      } 
    } catch (Exception e) {
      e.printStackTrace();
      response.getWriter().println("Error generating Excel file: " + e.getMessage());
    } 
  }
}
