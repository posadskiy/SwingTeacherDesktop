/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.dao;


import dev.dposadsky.java.swingteacherdesktop.tables.User;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DPosadsky
 */
public interface UserDao {
    
    public void addUser(User user) throws SQLException;
    public void deleteUser(User user) throws SQLException;
    public void deleteUser(int id) throws SQLException;
    public User getUserById(int id) throws SQLException;
    public User getUserByLoginAndPassword(String login, String pass) throws SQLException;
    public List<User> getUsers() throws SQLException;
    
}
