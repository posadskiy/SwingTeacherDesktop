package dev.dposadsky.java.swingteacherdesktop.controllers.swing;

import dev.dposadsky.java.swingteacherdesktop.controllers.swing.auth.AuthLoginService;
import dev.dposadsky.java.swingteacherdesktop.controllers.swing.auth.LoginListener;
import dev.dposadsky.java.swingteacherdesktop.main.Factory;
import dev.dposadsky.java.swingteacherdesktop.views.swing.AuthPane;
import dev.dposadsky.java.swingteacherdesktop.views.swing.RegistrationPane;

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
