/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.controllers;

import dev.dposadsky.java.swingteacherdesktop.dao.UserDao;
import dev.dposadsky.java.swingteacherdesktop.email.SenderTLS;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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


        if (checker.checkPassword(password, passwordRepeat)) {
            registrationPane.getPasswordField().setBorder(null);
            registrationPane.getPasswordRepeatField().setBorder(null);
            user.setPassword(StringUtils.md5Apache(password));
        }
        else {
            registrationPane.getPasswordField().setBorder(BorderFactory.createLineBorder(Color.red, 1));
            registrationPane.getPasswordRepeatField().setBorder(BorderFactory.createLineBorder(Color.red, 1));
            condition = false;
        }

        if (checker.checkEMail(eMail)) {
            registrationPane.getEMailTextField().setBorder(null);
            user.setEmail(eMail);
        }
        else {
            registrationPane.getEMailTextField().setBorder(BorderFactory.createLineBorder(Color.red, 1));
            condition = false;
        }
        
        if (!condition)
            return false;
          
        SenderTLS sender = factory.getSenderTLS();
        int randomCode = (int) (Math.random()*10000000);
        sender.send("Регистрация", "Ваш код: " + randomCode, "swingteacherru@gmail.com", eMail);
        
        String returnString = (JOptionPane.showInputDialog(new JFrame(), 
                            "На Ваш e-mail должен прийти проверочный код. Введите его:", 
                            "Подтверждение регистрации", JOptionPane.DEFAULT_OPTION));
        if (!checker.isNumeric(returnString)) 
            return false;
        
        int returnCode = Integer.parseInt(returnString);
        
        if (returnCode != randomCode) 
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
