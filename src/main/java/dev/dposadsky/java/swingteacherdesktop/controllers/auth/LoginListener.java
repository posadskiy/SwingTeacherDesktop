/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.controllers.auth;

import dev.dposadsky.java.swingteacherdesktop.views.MainFrameView;

/**
 *
 * @author DPosadsky
 */
public class LoginListener {

    public void loginFailed() {
        System.out.println("Failed");
    }

    public void loginStarted() {
        System.out.println("Start");
    }

    public void loginCanceled() {
        System.out.println("Cancel");
    }

    public void loginSucceeded() {
        MainFrameView mainFrameView = new MainFrameView();    
    }
    
}
