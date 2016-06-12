/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.utils;

import java.awt.BorderLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


/**
 *
 * @author DPosadsky
 */
public class Test {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JButton button = new JButton("Jessy", new ImageIcon("res/img/button/original.png"));
        //button.setPressedIcon(new ImageIcon("res/img/button/pressed.png"));
        button.setRolloverIcon(new ImageIcon("res/img/button/1.jpg"));
        //button.setDisabledIcon(new ImageIcon("res/img/button/1.jpg"));
        //button.setDisabledSelectedIcon(new ImageIcon("res/img/button/1.jpg"));
        //button.setRolloverSelectedIcon(null);
        button.setIconTextGap(400);
        //button.setEnabled(false);
        //button.setSelected(true);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMargin(new Insets(200, 200, 200, 200));
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        
        BorderLayout layout = new BorderLayout();
        frame.setLayout(layout);
        frame.add(button, BorderLayout.NORTH);
        //frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
