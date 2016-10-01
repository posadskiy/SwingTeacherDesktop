/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.utils;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 *
 * @author DPosadsky
 */
public class Test {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JButton button = new JButton();
        JButton button2 = new JButton();
        
        button.setText("Button1");
        button2.setText("Button2");
        
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        
        button2.setHorizontalAlignment(SwingConstants.RIGHT);
        button2.setVerticalAlignment(SwingConstants.BOTTOM);

        
        GridLayout borderLayout = new GridLayout(1,2);
        frame.setLayout(borderLayout);
        frame.add(button);
        frame.add(button2);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
