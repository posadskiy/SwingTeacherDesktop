/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.controllers;

import dev.dposadsky.java.swingteacherdesktop.controllers.auth.AuthLoginService;
import dev.dposadsky.java.swingteacherdesktop.controllers.auth.LoginListener;
import dev.dposadsky.java.swingteacherdesktop.dao.UserDao;
import dev.dposadsky.java.swingteacherdesktop.main.Factory;
import dev.dposadsky.java.swingteacherdesktop.views.AuthPane;
import dev.dposadsky.java.swingteacherdesktop.views.RegistrationPane;

/**
 *
 * @author DPosadsky
 */
public class AuthPaneController {
    
    private Factory factory;
    
    private LoginListener loginListenerImpl;
    private AuthLoginService authLoginService;
    
    private RegistrationPane registrationPane;
    
    public void okClickButton(String login, String pass) {
        factory = Factory.getInstance();
        
        loginListenerImpl = factory.getLoginListener();
        authLoginService = factory.getAuthLoginService();
        if (authLoginService.authenticate(login, pass)) {
            factory.getAuthPane().setVisible(false);
            loginListenerImpl.loginSucceeded();
        }
        else
            loginListenerImpl.loginFailed();
      
    }
    
    public void registrationPaneSetVisible(boolean b) {
        factory = Factory.getInstance();
        registrationPane = factory.getRegistrationPane();
        registrationPane.setVisible(b);
    }
    
    public void errorSetVisible(boolean b) {
        Factory factory = Factory.getInstance();
        AuthPane authPane = factory.getAuthPane();
        authPane.getErrorLabel().setVisible(b);
    }
    
}
