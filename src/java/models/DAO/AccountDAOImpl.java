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
import workconstants.AccountConstants;

/**
 *
 * @author Huynh Anh Kiet
 */
public class AccountDAOImpl implements AccountDAO {

    @Override
    public Account read(String email, String password) throws SQLException, ClassNotFoundException { //Act as login
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        String sql = "SELECT * FROM dbo.Accounts WHERE email=? AND password=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, email);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            int accID = rs.getInt("accID");
            String name = rs.getString("fullname");
            String phone = rs.getString("phone");
            int status = rs.getInt("status");
            int role = rs.getInt("role");
            conn.close();
            return new Account(accID, name, email, password, phone, status, role);
        };
        conn.close();
        return null;
    }

    public Account readCookie(String cookie) throws SQLException, ClassNotFoundException {
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        String sql = "SELECT * FROM dbo.Accounts WHERE token=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, cookie);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            int accID = rs.getInt("accID");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String name = rs.getString("fullname");
            String phone = rs.getString("phone");
            int status = rs.getInt("status");
            int role = rs.getInt("role");
            conn.close();
            return new Account(accID, name, email, password, phone, status, role);
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

    public boolean update(String mode, String value, int accId) throws SQLException, ClassNotFoundException {
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        String insertMode = "";
        switch (mode) {
            case AccountConstants.CHANGEEMAIL:
                insertMode = mode;
                break;
            case AccountConstants.CHANGENAME:
                insertMode = mode;
                break;

            case AccountConstants.CHANGEPASSWORD:
                insertMode = mode;
                break;

            case AccountConstants.CHANGEPHONE:
                insertMode = mode;
                break;
        }
        String sql = "UPDATE dbo.Accounts\n"
                + "SET " + insertMode + " = ?\n"
                + "WHERE accID = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, value);
        pstm.setInt(2, accId);
        if (pstm.executeUpdate() != 0) {
            conn.close();
            return true;
        }
        conn.close();
        return false;
    }

    public boolean statusChange(String accID, String status) throws SQLException, ClassNotFoundException {
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        String sql = "UPDATE dbo.Accounts\n"
                + "SET " + AccountConstants.CHANGESTATUS + " = ?\n"
                + "WHERE accID = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        int stat = Integer.parseInt(status) == 1 ? 0 : 1;
        int id = Integer.parseInt(accID);
        pstm.setInt(1, stat);
        pstm.setInt(2, id);
        if (pstm.executeUpdate() != 0) {
            conn.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean delete() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Account> readAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT *\n"
                + "from dbo.Accounts";
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        ArrayList<Account> accountList = new ArrayList();
        while (rs.next()) {
            int accID = rs.getInt("accID");
            String email = rs.getString("email");
            String pwd = rs.getString("password");
            String name = rs.getString("fullname");
            String phone = rs.getString("phone");
            int status = rs.getInt("status");
            int role = rs.getInt("role");
            accountList.add(new Account(accID, name, email, pwd, phone, status, role));
        }
        conn.close();
        if (!accountList.isEmpty()) {
            return accountList;
        }
        return new ArrayList<Account>();
    }

    @Override
    public boolean save() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean saveCookie(String cookie, int userID) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE dbo.Accounts \n"
                + "SET token = ?\n"
                + "WHERE accID = ?";
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, cookie);
        pstm.setInt(2, userID);
        if (pstm.executeUpdate() > 0) {
            conn.close();
            return true;
        }
        conn.close();
        return false;
    }
}
