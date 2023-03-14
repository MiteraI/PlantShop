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
import models.DAOInterface.OrderDetailDAO;
import models.entities.OrderDetail;
import models.entities.Plant;
import models.entities.UOrder;
import workconstants.OrderConstants;

/**
 *
 * @author Huynh Anh Kiet
 */
public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public OrderDetail read(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public OrderDetail read(int accID, String odID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT\n"
                + "    OrderDetails.DetailId,\n"
                + "    OrderDetails.OrderID,\n"
                + "    OrderDetails.PID,\n"
                + "    Plants.PName,\n"
                + "    Plants.price,\n"
                + "    Plants.imgPath,\n"
                + "    OrderDetails.quantity,\n"
                + "    Orders.OrdDate,\n"
                + "    Orders.shipdate,\n"
                + "    Orders.status\n"
                + "FROM\n"
                + "    OrderDetails\n"
                + "    INNER JOIN Orders ON OrderDetails.OrderID = Orders.OrderID\n"
                + "    INNER JOIN Plants ON OrderDetails.PID = Plants.PID\n"
                + "WHERE\n"
                + "    Orders.AccID = ? AND OrderDetails.DetailId = ?\n"
                + "select * from dbo.Orders";

        Connection conn = dbconnect.ConnectionUtils.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, accID);
        pstm.setInt(2, Integer.parseInt(odID));
        ResultSet rs = pstm.executeQuery();
        UOrder uOrder = null;
        Plant plant = null;
        OrderDetail orderDetail = null;
        while (rs.next()) {
            int orderDetailID = rs.getInt("DetailId");
            int orderID = rs.getInt("OrderID");
            int plantID = rs.getInt("PID");
            String plantName = rs.getString("PName");
            double price = rs.getDouble("price");
            String imgPath = rs.getString("imgPath");
            int quantity = rs.getInt("quantity");
            String orderDate = rs.getString("OrdDate");
            String shippingDate = rs.getString("shipdate");
            int status = rs.getInt("status");
            uOrder = new UOrder(orderID, orderDate, shippingDate, status);
            plant = new Plant(accID, plantName, price, imgPath);
            orderDetail = new OrderDetail(orderDetailID, uOrder, plant, quantity);
        }
        conn.close();
        if (orderDetail != null) {
            return orderDetail;
        }
        return null;
    }

    @Override
    public boolean create(String accID, String PID, String quantity) throws SQLException, ClassNotFoundException {
        //Basically purchase function
        String sql = "INSERT INTO Orders (OrdDate, shipdate, status, AccID) \n"
                + "VALUES (GETDATE(), DATEADD(WEEK,1,GETDATE()), 1, ?);\n" //Account ID take from session
                + "\n"
                + "INSERT INTO OrderDetails (OrderID, PID, quantity)\n"
                + "VALUES ((SELECT MAX(OrderID) FROM Orders), ? , ?);"; //PID and quanity take from cart
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, accID);
        pstm.setString(2, PID);
        pstm.setString(3, quantity);
        if (pstm.executeUpdate() > 0) {
            conn.commit();
            conn.setAutoCommit(true);
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

    public boolean updateUserOrder(String action, String orderId, int userId) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE dbo.Orders\n"
                + "SET status = ?\n"
                + "WHERE orderID = ? AND accID = ?;"; //PID and quanity take from cart
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        switch (action) {
            case OrderConstants.CANCEL:
                pstm.setInt(1, 3);
                break;
            case OrderConstants.REORDER:
                if (reOrder(orderId, userId)) {
                    return true;
                }
                return false;
        }
        pstm.setInt(2, Integer.parseInt(orderId));
        pstm.setInt(3, userId);
        if (pstm.executeUpdate() > 0) {
            conn.close();
            return true;
        }
        conn.close();
        return false;
    }

    // reOrder function because if someone reorder then the order date should change
    private boolean reOrder(String orderId, int userId) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Orders\n"
                + "SET status = 1, OrdDate = GETDATE(), shipdate = DATEADD(WEEK,1,GETDATE())\n"
                + "WHERE OrderID = ? AND accID = ?;";
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, Integer.parseInt(orderId));
        pstm.setInt(2, userId);
        if (pstm.executeUpdate() > 0) {
            conn.close();
            return true;
        }
        conn.close();
        return false;
    }
    
    public boolean complete(String orderId) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Orders\n"
                + "SET status = 2\n"
                + "WHERE OrderID = ?";
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, Integer.parseInt(orderId));
        if (pstm.executeUpdate() > 0) {
            conn.close();
            return true;
        }
        conn.close();
        return false;
    }
    
    @Override
    public boolean delete() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean delete(String orderID) throws SQLException, ClassNotFoundException {
        String sql = "DELETE dbo.OrderDetails\n"
                + "WHERE OrderID = ?\n"
                + "DELETE FROM dbo.Orders\n"
                + "WHERE OrderID = ?";
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, Integer.parseInt(orderID));
        pstm.setInt(2, Integer.parseInt(orderID));
        if (pstm.executeUpdate() > 0) {
            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
            return true;
        }
        conn.close();
        return false;
    }

    @Override
    public boolean save() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<OrderDetail> readAll(int accID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT\n"
                + "    OrderDetails.DetailId,\n"
                + "    OrderDetails.OrderID,\n"
                + "    OrderDetails.PID,\n"
                + "    Plants.PName,\n"
                + "    Plants.price,\n"
                + "    Plants.imgPath,\n"
                + "    OrderDetails.quantity,\n"
                + "    Orders.OrdDate,\n"
                + "    Orders.shipdate,\n"
                + "    Orders.status\n"
                + "FROM\n"
                + "    OrderDetails\n"
                + "    INNER JOIN Orders ON OrderDetails.OrderID = Orders.OrderID\n"
                + "    INNER JOIN Plants ON OrderDetails.PID = Plants.PID\n"
                + "WHERE\n"
                + "    Orders.AccID = ?;\n";
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, accID);
        ResultSet rs = pstm.executeQuery();
        Plant plant = null;
        UOrder uOrder = null;
        OrderDetail orderDetail = null;
        ArrayList<OrderDetail> orderList = new ArrayList();
        while (rs.next()) {
            int orderDetailID = rs.getInt("DetailId");
            int orderID = rs.getInt("OrderID");
            int plantID = rs.getInt("PID");
            String plantName = rs.getString("PName");
            double price = rs.getDouble("price");
            String imgPath = rs.getString("imgPath");
            int quantity = rs.getInt("quantity");
            String orderDate = rs.getString("OrdDate");
            String shippingDate = rs.getString("shipdate");
            int status = rs.getInt("status");
            plant = new Plant(plantID, plantName, price, imgPath);
            uOrder = new UOrder(orderID, orderDate, shippingDate, status);
            orderDetail = new OrderDetail(orderDetailID, uOrder, plant, quantity);
            orderList.add(orderDetail);
        }
        conn.close();
        if (!orderList.isEmpty()) {
            return orderList;
        }
        return new ArrayList<OrderDetail>();
    }

    public ArrayList<OrderDetail> readAllAdmin() throws SQLException, ClassNotFoundException {
        String sql = "SELECT\n"
                + "    OrderDetails.DetailId,\n"
                + "    OrderDetails.OrderID,\n"
                + "    OrderDetails.PID,\n"
                + "    Plants.PName,\n"
                + "    Plants.price,\n"
                + "    Plants.imgPath,\n"
                + "    OrderDetails.quantity,\n"
                + "    Orders.OrdDate,\n"
                + "    Orders.shipdate,\n"
                + "    Orders.AccID,\n"
                + "    Orders.status\n"
                + "FROM\n"
                + "    OrderDetails\n"
                + "    INNER JOIN Orders ON OrderDetails.OrderID = Orders.OrderID\n"
                + "    INNER JOIN Plants ON OrderDetails.PID = Plants.PID\n";
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        Plant plant = null;
        UOrder uOrder = null;
        OrderDetail orderDetail = null;
        ArrayList<OrderDetail> orderList = new ArrayList();
        while (rs.next()) {
            int orderDetailID = rs.getInt("DetailId");
            int orderID = rs.getInt("OrderID");
            int plantID = rs.getInt("PID");
            String plantName = rs.getString("PName");
            double price = rs.getDouble("price");
            String imgPath = rs.getString("imgPath");
            int quantity = rs.getInt("quantity");
            String orderDate = rs.getString("OrdDate");
            String shippingDate = rs.getString("shipdate");
            int accID = rs.getInt("AccID");
            int status = rs.getInt("status");
            plant = new Plant(plantID, plantName, price, imgPath);
            uOrder = new UOrder(orderID, orderDate, shippingDate, status, accID);
            orderDetail = new OrderDetail(orderDetailID, uOrder, plant, quantity);
            orderList.add(orderDetail);
        }
        conn.close();
        if (!orderList.isEmpty()) {
            return orderList;
        }
        return new ArrayList<OrderDetail>();
    }
}
