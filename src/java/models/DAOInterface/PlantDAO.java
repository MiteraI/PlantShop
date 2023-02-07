/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package models.DAOInterface;

import java.sql.SQLException;
import java.util.ArrayList;
import models.entities.Plant;

/**
 *
 * @author Huynh Anh Kiet
 */
public interface PlantDAO {

    Plant read(String string1, String string2) throws SQLException, ClassNotFoundException;

    boolean create() throws SQLException, ClassNotFoundException;

    boolean update() throws SQLException, ClassNotFoundException;

    boolean delete() throws SQLException, ClassNotFoundException;

    //Others
    boolean save() throws SQLException, ClassNotFoundException;

    public ArrayList<Plant> readAll() throws SQLException, ClassNotFoundException;
}
