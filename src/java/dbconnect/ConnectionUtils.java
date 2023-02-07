/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbconnect;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Huynh Anh Kiet
 */
public class ConnectionUtils {

    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {

        // Ở đây tôi kết nối tới Oracle Database.
        // (Bạn có thể thay đổi sử dụng database khác).
        return SQLServerConnection.getSQLServerConnection();

        // return OracleConnUtils.getOracleConnection();
        // return MySQLConnUtils.getMySQLConnection();
        // return SQLServerConnUtils_JTDS.getSQLServerConnection_JTDS();
        // return SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
        // return PostGresConnUtils.getPostGresConnection();
    }

    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
