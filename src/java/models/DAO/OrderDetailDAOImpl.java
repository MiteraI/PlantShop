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

/**
 *
 * @author Huynh Anh Kiet
 */
public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public OrderDetail read(String id) throws SQLException, ClassNotFoundException {
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
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, accID);
        pstm.setString(2, PID);
        pstm.setString(3, quantity);
        if (pstm.executeUpdate() > 0) {
            conn.close();
            return true;
        }
        return false;
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
    public boolean save() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<OrderDetail> readAll(String accID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT\n"
                + "    OrderDetails.DetailId,\n"
                + "    OrderDetails.OrderID,\n"
                + "    OrderDetails.PID,\n"
                + "    Plants.PName,\n"
                + "    Plants.price,\n"
                + "    Plants.imgPath,\n"
                + "    OrderDetails.quantity,\n"
                + "	Orders.OrdDate,\n"
                + "	Orders.shipdate\n"
                + "FROM\n"
                + "    OrderDetails\n"
                + "    INNER JOIN Orders ON OrderDetails.OrderID = Orders.OrderID\n"
                + "    INNER JOIN Plants ON OrderDetails.PID = Plants.PID\n"
                + "WHERE\n"
                + "    Orders.AccID = ?;\n"
                + "select * from dbo.Orders";
        Connection conn = dbconnect.ConnectionUtils.getConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, accID);
        ResultSet rs = pstm.executeQuery();
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
            orderList.add(new OrderDetail(orderDetailID, orderID, plantID, plantName, price, imgPath, quantity, orderDate, shippingDate));
        }
        conn.close();
        if (!orderList.isEmpty()) {
            return orderList;
        }
        return new ArrayList<OrderDetail>();
    }

}
