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
    private UserDao userDao;
    
    LoginListener loginListenerImpl;
    AuthLoginService authLoginService;
    
    AuthPane authPane;
    RegistrationPane registrationPane;
    
    public void okClickButton(String login, String pass) {
        factory = Factory.getInstance();
        userDao = factory.getUserDao();
        
        loginListenerImpl = new LoginListener();
        authLoginService = new AuthLoginService();
        if (authLoginService.authenticate(login, pass)) {
            authPane.setVisible(false);
            loginListenerImpl.loginSucceeded();
        }
        else
            loginListenerImpl.loginFailed();
      
    }
    
    public void registrationPaneSetVisible(boolean b) {
        if (registrationPane == null)
            registrationPane = new RegistrationPane();
        registrationPane.setVisible(b);
    }
    
    public void setAuthPane(AuthPane authPane) {
        this.authPane = authPane;
    }
    
}
