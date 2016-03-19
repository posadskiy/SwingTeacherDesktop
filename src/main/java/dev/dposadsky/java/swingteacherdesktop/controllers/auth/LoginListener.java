/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.controllers.auth;

import dev.dposadsky.java.swingteacherdesktop.controllers.AuthPaneController;
import dev.dposadsky.java.swingteacherdesktop.main.Factory;
import dev.dposadsky.java.swingteacherdesktop.views.MainFrameView;

/**
 *
 * @author DPosadsky
 */
public class LoginListener {

    public void loginFailed() {
        Factory factory = Factory.getInstance();
        AuthPaneController controller = factory.getAuthPaneController();
        controller.errorSetVisible(true);
    }

    public void loginStarted() {
        System.out.println("Start");
    }

    public void loginCanceled() {
        System.out.println("Cancel");
    }

    public void loginSucceeded() {
        Factory factory = Factory.getInstance();
        AuthPaneController controller = factory.getAuthPaneController();
        controller.errorSetVisible(false);
        MainFrameView mainFrameView = new MainFrameView();    
    }
    
}
