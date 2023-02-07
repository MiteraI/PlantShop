/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package models.DAOInterface;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Huynh Anh Kiet
 */
public interface DAO<T> {

    //CRUD pattern
    T read(String string1, String string2) throws SQLException, ClassNotFoundException;

    boolean create() throws SQLException, ClassNotFoundException;

    boolean update() throws SQLException, ClassNotFoundException;

    boolean delete() throws SQLException, ClassNotFoundException;

    //Others
    boolean save() throws SQLException, ClassNotFoundException;
    
    ArrayList<T> readAll() throws SQLException, ClassNotFoundException;

}
