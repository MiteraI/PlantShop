/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnection {

    // Kết nối vào SQLServer.
    // (Sử dụng thư viện điều khiển JDBC)
    public static Connection getSQLServerConnection() //
            throws SQLException, ClassNotFoundException {

        // Chú ý: Thay đổi các thông số kết nối cho phù hợp.
        String hostName = "localhost";
        String sqlInstanceName = "DESKTOP-89R1CLH\\SQLEXPRESS";
        String database = "PlantShop";
        String userName = "sa";
        String password = "12345";

        return getSQLServerConnection(hostName, sqlInstanceName, database, userName, password);
    }

    // Kết nối tới SQL Server sử dụng thư viện JDBC.
    private static Connection getSQLServerConnection(String hostName, //
            String sqlInstanceName, String database, String userName, String password)
            throws ClassNotFoundException, SQLException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // Cấu trúc URL Connection đối với SQL Server:
        // Ví dụ:
        // jdbc:jtds:sqlserver://localhost:1433/simplehr;instance=SQLEXPRESS
        String connectionURL = "jdbc:sqlserver://" + hostName + ":1433"
                + ";instance=" + sqlInstanceName + ";databaseName=" + database
                + ";encrypt=true;trustServerCertificate=true";
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }

}
