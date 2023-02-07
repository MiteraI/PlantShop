/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package models.DAOInterface;

import java.sql.SQLException;
import java.util.ArrayList;
import models.entities.Account;

/**
 *
 * @author Huynh Anh Kiet
 */
public interface AccountDAO{

    public Account read(String email, String password) throws SQLException, ClassNotFoundException;

    boolean createUser(String email, String name, String password, String phone) throws SQLException, ClassNotFoundException;

    boolean createAccount(String email, String name, String password, String phone, int role) throws SQLException, ClassNotFoundException;

    public boolean update() throws SQLException, ClassNotFoundException;

    boolean delete() throws SQLException, ClassNotFoundException;

    boolean save() throws SQLException, ClassNotFoundException;

    ArrayList<Account> readAll() throws SQLException, ClassNotFoundException;

}
