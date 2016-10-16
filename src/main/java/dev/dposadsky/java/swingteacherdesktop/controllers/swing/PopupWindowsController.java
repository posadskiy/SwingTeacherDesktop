/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.controllers.swing;

import dev.dposadsky.java.swingteacherdesktop.main.Factory;
import dev.dposadsky.java.swingteacherdesktop.views.swing.PopupWindowsView;
import javax.swing.JFrame;

/**
 *
 * @author DPosadsky
 */
public class PopupWindowsController {
    
    public void createPopupWindow(JFrame frame, String message, String title, int option) {
        Factory factory = Factory.getInstance();
        PopupWindowsView popupWindowsView = factory.getPopupWindowsView();
        
        popupWindowsView.createOptionPane(frame, message, title, option);
        
    }
    
    public void createPopupWindow(JFrame frame, String message, String title) {
        Factory factory = Factory.getInstance();
        PopupWindowsView popupWindowsView = factory.getPopupWindowsView();
        
        popupWindowsView.createOptionPane(frame, message, title);
        
    }
    
    public void createPopupWindow(String message, String title) {
        Factory factory = Factory.getInstance();
        PopupWindowsView popupWindowsView = factory.getPopupWindowsView();
        
        popupWindowsView.createOptionPane(message, title);
        
    }
    
}
