/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.controllers;

import dev.dposadsky.java.swingteacherdesktop.dao.UserDao;
import dev.dposadsky.java.swingteacherdesktop.main.Checker;
import dev.dposadsky.java.swingteacherdesktop.main.Factory;
import dev.dposadsky.java.swingteacherdesktop.tables.User;
import dev.dposadsky.java.swingteacherdesktop.utils.StringUtils;
import dev.dposadsky.java.swingteacherdesktop.views.RegistrationPane;
import java.awt.Color;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

/**
 *
 * @author DPosadsky
 */
public class RegistrationPaneController {
    
    private RegistrationPane registrationPane;
    
    private Factory factory;
    private UserDao userDao;
    
    public boolean registration(String login, String password, String passwordRepeat, String eMail) {
        factory = Factory.getInstance();
        userDao = factory.getUserDao();
        
        Checker checker = factory.getChecker();
        registrationPane = factory.getRegistrationPane();
        
        boolean condition = true;
        
        User user = factory.getCurrentUser();

        if (checker.checkLogin(login)) {
            registrationPane.getLoginTextField().setBorder(null);
            user.setLogin(login);
        }
        else {
            registrationPane.getLoginTextField().setBorder(BorderFactory.createLineBorder(Color.red, 1));
            condition = false;
        }


        if (checker.checkPassword(password, passwordRepeat))
            user.setPassword(StringUtils.md5Apache(password));
        else {
            registrationPane.getPasswordField().setBorder(BorderFactory.createLineBorder(Color.red, 1));
            registrationPane.getPasswordRepeatField().setBorder(BorderFactory.createLineBorder(Color.red, 1));
            condition = false;
        }

        if (checker.checkEMail(eMail))
            user.setEmail(eMail);
        else {
            registrationPane.getEMailTextField().setBorder(BorderFactory.createLineBorder(Color.red, 1));
            condition = false;
        }
        
        if (!condition)
            return false;
            
        try {
            userDao.addUser(user);
        } catch (SQLException ex) {
            System.out.println("Exception in registriation in RegistrationPaneController: " + ex);
            return false;
        }
        
        return true;
    }
    
    public void closePane() {
        Factory factory = Factory.getInstance();
        factory.getRegistrationPane().setVisible(false);
    }
    
}
