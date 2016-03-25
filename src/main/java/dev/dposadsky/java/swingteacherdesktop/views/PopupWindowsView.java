/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.views;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author DPosadsky
 */
public class PopupWindowsView {
    
    public void createOptionPane(JFrame frame, String message, String title, int option) {
        JOptionPane.showMessageDialog(frame, message, title, option);
    }
    
    public void createOptionPane(JFrame frame, String message, String title) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.DEFAULT_OPTION);
    }
    
    public void createOptionPane(String message, String title) {
        JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.DEFAULT_OPTION);
    }

}
