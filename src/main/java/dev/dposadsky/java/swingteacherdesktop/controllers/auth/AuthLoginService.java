/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.controllers.auth;

import dev.dposadsky.java.swingteacherdesktop.dao.UserDao;
import dev.dposadsky.java.swingteacherdesktop.main.Factory;
import dev.dposadsky.java.swingteacherdesktop.tables.User;
import dev.dposadsky.java.swingteacherdesktop.utils.StringUtils;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DPosadsky
 */
public class AuthLoginService {

    public boolean authenticate(String login, String pass) {
        Factory factory = Factory.getInstance();
        UserDao userDao = factory.getUserDao();

        User user = new User(); 
        try {
            user = userDao.getUserByLoginAndPassword(login, StringUtils.md5Apache(pass));
        } catch (SQLException ex) {
            System.out.println("Exception in authenticate in AuthLoginService " + ex);
        }
        
        if (user == null)
            return false;
        
        factory.serCurrentUser(user);
        
        return true;
    }
    
}
