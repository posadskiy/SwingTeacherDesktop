/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.controllers;

import dev.dposadsky.java.swingteacherdesktop.dao.UserDao;
import dev.dposadsky.java.swingteacherdesktop.main.Factory;
import dev.dposadsky.java.swingteacherdesktop.tables.User;
import dev.dposadsky.java.swingteacherdesktop.utils.StringUtils;
import dev.dposadsky.java.swingteacherdesktop.views.RegistrationPane;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DPosadsky
 */
public class RegistrationPaneController {
    
    private RegistrationPane registrationPane;
    
    private Factory factory;
    private UserDao userDao;
    
    public boolean registration(String login, String pass, String eMail) {
        
        factory = Factory.getInstance();
        userDao = factory.getUserDao();
        
        User user = factory.getCurrentUser();
        user.setLogin(login);
        user.setPassword(StringUtils.md5Apache(pass));
        user.setEmail(eMail);
        
        try {
            userDao.addUser(user);
        } catch (SQLException ex) {
            System.out.println("Exception in registriation in RegistrationPaneController: " + ex);
            return false;
        }
        
        return true;
    }
    
    public void closePane() {
        registrationPane.setVisible(false);
    }
    
    public void setRegistrationPane(RegistrationPane registrationPane) {
        this.registrationPane = registrationPane;
    }
    
}
