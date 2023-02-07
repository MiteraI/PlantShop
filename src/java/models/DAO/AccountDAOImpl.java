/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.DAOInterface.AccountDAO;
import models.entities.Account;

/**
 *
 * @author Huynh Anh Kiet
 */
public class AccountDAOImpl implements AccountDAO {

    @Override
    public Account read(String email, String password) throws SQLException, ClassNotFoundException {
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        String sql = "SELECT * FROM dbo.Accounts WHERE email=? AND password=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, email);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            String name = rs.getString("fullname");
            String phone = rs.getString("phone");
            int status = rs.getInt("status");
            int role = rs.getInt("role");
            conn.close();
            return new Account(name, email, password, phone, status, role);
        };
        conn.close();
        return null;
    }

    @Override
    public boolean createUser(String email, String name, String password, String phone) throws SQLException, ClassNotFoundException {
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        String sql = "INSERT INTO Accounts (email, password, fullname, phone, status, role)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?);";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, email);
        pstm.setString(2, password);
        pstm.setString(3, name);
        pstm.setString(4, phone);
        pstm.setInt(5, 1);
        pstm.setInt(6, 0);
        if (pstm.executeUpdate() > 0) {
            conn.close();
            return true;
        }
        conn.close();
        return false;
    }

    @Override
    public boolean createAccount(String string, String string1, String string2, String string3, int i) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Account> readAll() throws SQLException, ClassNotFoundException {
        return new ArrayList<Account>();
    }

    @Override
    public boolean save() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
