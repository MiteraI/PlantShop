/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.DAOInterface.CategoryDAO;
import models.entities.Category;

/**
 *
 * @author Huynh Anh Kiet
 */
public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public Object read(String string1, String string2) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean create() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean create(String name) throws SQLException, ClassNotFoundException {
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        String sql = "INSERT INTO dbo.Categories (CateName)\n"
                + "VALUES\n"
                + "(?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, name);
        if (pstm.executeUpdate() != 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean update(String name, String id) throws SQLException, ClassNotFoundException {
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        String sql = "UPDATE dbo.Categories\n"
                + "SET CateName = ?\n"
                + "WHERE CateID = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, name);
        pstm.setInt(2, Integer.parseInt(id));
        if (pstm.executeUpdate() != 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        String sql = "DELETE FROM dbo.Categories\n"
                + "WHERE CateID = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, Integer.parseInt(id));
        if (pstm.executeUpdate() != 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean save() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList readAll() throws SQLException, ClassNotFoundException {
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        String sql = "SELECT * FROM dbo.Categories";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        ArrayList<Category> cateList = new ArrayList();
        while (rs.next()) {
            int id = rs.getInt("CateID");
            String name = rs.getString("CateName");
            cateList.add(new Category(id, name));
        }
        if (!cateList.isEmpty()) {
            return cateList;
        }
        return new ArrayList<Category>();
    }

}
