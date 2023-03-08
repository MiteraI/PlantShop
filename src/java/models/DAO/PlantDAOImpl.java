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
import models.DAOInterface.PlantDAO;
import models.entities.Category;
import models.entities.Plant;

/**
 *
 * @author Huynh Anh Kiet
 */
public class PlantDAOImpl implements PlantDAO {

    @Override
    public Plant read(String name, String random) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM dbo.Plants WHERE PName = ?";
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, name);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("PID");
            String plantName = rs.getString("PName");
            double price = rs.getDouble("price");
            String imgPath = rs.getString("imgPath");
            String description = rs.getString("description");
            int status = rs.getInt("status");
            Plant plant = new Plant(id, plantName, price, imgPath, description, status);
            conn.close();
            return plant;
        }
        conn.close();
        return null;
    }

    public Plant read(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM dbo.Plants WHERE PID = ?";
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            int pid = rs.getInt("PID");
            String plantName = rs.getString("PName");
            double price = rs.getDouble("price");
            String imgPath = rs.getString("imgPath");
            String description = rs.getString("description");
            int status = rs.getInt("status");
            Plant plant = new Plant(pid, plantName, price, imgPath, description, status);
            conn.close();
            return plant;
        }
        conn.close();
        return null;
    }

    @Override
    public boolean create() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean create(String PName, String price, String description, String CateID) throws SQLException, ClassNotFoundException {
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        String sql = "INSERT INTO Plants (PName, price, imgPath, description, status, cateID)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?);";
        String imgPath = PName.toLowerCase() + ".jpg";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, PName);
        pstm.setDouble(2, Double.parseDouble(price));
        pstm.setString(3, imgPath); //Format "plantname.jpg"
        pstm.setString(4, description);
        pstm.setInt(5, 1); //Status always start with in stock
        pstm.setInt(6, Integer.parseInt(CateID));
        if (pstm.executeUpdate() > 0) {
            conn.close();
            return true;
        }
        conn.close();
        return false;
    }

    @Override
    public boolean update() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean statusChange(String PID, String status) throws SQLException, ClassNotFoundException {
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        String sql = "UPDATE dbo.Plants\n"
                + "SET status = ?\n"
                + "WHERE PID = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        int stat = Integer.parseInt(status) == 1 ? 0 : 1;
        int id = Integer.parseInt(PID);
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

    public boolean delete(String PID) throws SQLException, ClassNotFoundException {
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        String sql = "DELETE FROM dbo.Plants\n"
                + "WHERE PID = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, Integer.parseInt(PID));
        if (pstm.executeUpdate() != 0) {
            conn.close();
            return true;
        }
        conn.close();
        return false;
    }

    @Override
    //Read all for general purpose like homepage...
    public ArrayList<Plant> readAll() throws SQLException, ClassNotFoundException {
        ArrayList<Plant> plantList = new ArrayList<>();
        String sql = "SELECT * FROM dbo.Plants";
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("PID");
            String plantName = rs.getString("PName");
            double price = rs.getDouble("price");
            String imgPath = rs.getString("imgPath");
            String description = rs.getString("description");
            int status = rs.getInt("status");
            plantList.add(new Plant(id, plantName, price, imgPath, description, status));
        }
        conn.close();
        if (!plantList.isEmpty()) {
            return plantList;
        }
        return new ArrayList<Plant>();
    }
    //Read all for admin, able to view category of plant

    public ArrayList<Plant> readAllAdmin() throws SQLException, ClassNotFoundException {
        ArrayList<Plant> plantList = new ArrayList<>();
        String sql = "SELECT Plants.PID,\n"
                + "Plants.PName,\n"
                + "Plants.price,\n"
                + "Plants.imgPath,\n"
                + "Plants.description,\n"
                + "Plants.status,\n"
                + "Categories.CateID,\n"
                + "Categories.CateName\n"
                + "FROM dbo.Plants\n"
                + "INNER JOIN dbo.Categories ON dbo.Plants.CateID = dbo.Categories.CateID";
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("PID");
            String plantName = rs.getString("PName");
            double price = rs.getDouble("price");
            String imgPath = rs.getString("imgPath");
            String description = rs.getString("description");
            int status = rs.getInt("status");
            int CateID = rs.getInt("CateID");
            String CateName = rs.getString("CateName");
            Category cate = new Category(CateID, CateName);
            Plant plant = new Plant(id, plantName, price, imgPath, description, status, cate);
            plantList.add(plant);
        }
        conn.close();
        if (!plantList.isEmpty()) {
            return plantList;
        }
        return new ArrayList<Plant>();
    }

    public ArrayList<Plant> search(String query, String mode) throws SQLException, ClassNotFoundException {
        ArrayList<Plant> plantList = new ArrayList<>();
        String searchMode = "name".equals(mode) ? "P.PName" : "C.CateName";
        String sql = "DECLARE @Name nvarchar(100) = ?\n"
                + "SELECT PID, PName, price, imgPath, description, status\n"
                + "FROM Plants P\n"
                + "JOIN Categories C ON P.CateID = C.CateID\n"
                + "WHERE (@Name IS NULL OR " + searchMode + " LIKE '%' + @Name + '%')";
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, query);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("PID");
            String name = rs.getString("PName");
            double price = rs.getDouble("price");
            String imgPath = rs.getString("imgPath");
            String description = rs.getString("description");
            int status = rs.getInt("status");
            plantList.add(new Plant(id, name, price, imgPath, description, status));
        }
        conn.close();
        if (!plantList.isEmpty()) {
            return plantList;
        }
        return new ArrayList<Plant>();
    }

    @Override
    public boolean save() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
