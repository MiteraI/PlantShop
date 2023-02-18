/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package models.DAOInterface;

import java.sql.SQLException;
import java.util.ArrayList;
import models.entities.OrderDetail;

/**
 *
 * @author Huynh Anh Kiet
 */
public interface OrderDetailDAO {
    OrderDetail read(String id) throws SQLException, ClassNotFoundException;

    boolean create(String accID, String PID, String quantity) throws SQLException, ClassNotFoundException;

    boolean update() throws SQLException, ClassNotFoundException;

    boolean delete() throws SQLException, ClassNotFoundException;

    //Others
    boolean save() throws SQLException, ClassNotFoundException;

    public ArrayList<OrderDetail> readAll(int accID) throws SQLException, ClassNotFoundException;
}
