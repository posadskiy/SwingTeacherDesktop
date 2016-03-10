/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.views;

import dev.dposadsky.java.swingteacherdesktop.controllers.AuthPaneController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author DPosadsky
 */
public class AuthPane extends JFrame{
    
    AuthPaneController controller;
        
    ImagePanel imgPanel;
    
    JLabel auth;
    JLabel loginLabel;
    JLabel passLabel;
    
    JTextField loginField;
    JPasswordField passField;
    
    JButton ok;
    JButton registration;
    JButton rememberPass;
    
    MigLayout layout;
    
    
    public AuthPane() {
        initComponent();
    }
    
    private void initComponent() {
        controller = new AuthPaneController();
        controller.setAuthPane(this);
        
        imgPanel = getImage();
        imgPanel.setPreferredSize(new Dimension(800,450));
        
        layout = new MigLayout("wrap 4", "grow, fill");

        imgPanel.setLayout(layout);
        
        setResizable(false);
        
        add(imgPanel);
        
        layout = new MigLayout("wrap 4", "grow, fill");
        setLayout(layout);

        loginLabel = new JLabel("Логин", SwingConstants.CENTER);
        loginLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        loginLabel.setForeground(Color.WHITE);
        passLabel = new JLabel("Пароль", SwingConstants.CENTER);
        passLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        passLabel.setForeground(Color.WHITE);
        
        loginField = new JTextField();
        loginField.setMinimumSize(new Dimension(100, 40));
        loginField.setFont(new Font("Verdana", Font.PLAIN, 20));
        passField = new JPasswordField();
        passField.setMinimumSize(new Dimension(100, 40));
        passField.setFont(new Font("Verdana", Font.PLAIN, 20));
        
        ok = new JButton("Вход");
        ok.addActionListener(new ActionListener() {      
            @Override
            public void actionPerformed(ActionEvent ae) {
                clickOkButton(ae);
            }
        });
        registration = new JButton("Регистрация");
        registration.addActionListener(new ActionListener() {      
            @Override
            public void actionPerformed(ActionEvent ae) {
                clickRegistrationButton(ae);
            }
        });
        rememberPass = new JButton("Забыли пароль?");
        rememberPass.addActionListener(new ActionListener() {      
            @Override
            public void actionPerformed(ActionEvent ae) {
                clickRememberPassButton(ae);
            }
        });
 
        imgPanel.add(loginLabel, "w 25%, gap 0px 0px 300px 45px");
        imgPanel.add(loginField, "w 25%");
        imgPanel.add(passLabel, "w 25%");
        imgPanel.add(passField, "w 25%");
        
        imgPanel.add(registration);
        imgPanel.add(rememberPass);
        
        imgPanel.add(ok, "span 2");
        
        doSetupFrame();
    }
    
    public void doSetupFrame() {
        setTitle("Авторизация");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 450);
        setVisible(true);
        //pack();
        setLocationRelativeTo(null);
    }
    
    public ImagePanel getImage() {
        ImagePanel img = new ImagePanel();
        img.setLayout(new BorderLayout());
        try {
            img.setImage(ImageIO.read(new File("res/img/login.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
    
    public void clickRegistrationButton(ActionEvent ae) {
        controller.registrationPaneSetVisible(true);
    }
    
    public void clickRememberPassButton(ActionEvent ae) {
        
    }
    
    public void clickOkButton(ActionEvent ae) {
        controller.okClickButton(loginField.getText(), String.copyValueOf(passField.getPassword()));
    }
    
    public static void main(String[] args) {
        AuthPane authPane = new AuthPane();
    }
}
