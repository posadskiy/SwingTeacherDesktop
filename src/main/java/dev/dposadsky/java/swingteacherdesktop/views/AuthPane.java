/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.views;

import dev.dposadsky.java.swingteacherdesktop.controllers.AuthPaneController;
import dev.dposadsky.java.swingteacherdesktop.main.Factory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
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
    JLabel errorLabel;
    
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
        Factory factory = Factory.getInstance();
        controller = factory.getAuthPaneController();
        
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
        errorLabel = new JLabel("Такого пользователя не существует. Проверьте введенные данные.", 
                SwingConstants.CENTER);
        errorLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
        errorLabel.setForeground(Color.red);
        errorLabel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.RED, 1, true),
                BorderFactory.createEmptyBorder(0,0,0,0)));
        errorLabel.setVisible(false);

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
 
        imgPanel.add(loginLabel, "w 25%, gap 0px 0px 300px 0px");
        imgPanel.add(loginField, "w 25%");
        imgPanel.add(passLabel, "w 25%");
        imgPanel.add(passField, "w 25%");
        
        imgPanel.add(errorLabel, "span 4");
        
        imgPanel.add(registration, "gap 0px 0px 10px 0px");
        imgPanel.add(rememberPass);
        
        imgPanel.add(ok, "span 2");
        
        doSetupFrame();
    }
    
    public void doSetupFrame() {
        setTitle("Авторизация");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 450);
        //pack();
        setLocationRelativeTo(null);
        setVisible(true);
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
    
    public JLabel getErrorLabel() {
        return errorLabel;
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
        Factory factory = Factory.getInstance();
        AuthPane authPane = factory.getAuthPane();   
    }
}
