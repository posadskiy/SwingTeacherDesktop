/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.views;

import dev.dposadsky.java.swingteacherdesktop.controllers.RegistrationPaneController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author DPosadsky
 */
public class RegistrationPane extends JFrame {
    
    JLabel titleLabel;
    JLabel loginLabel;
    JLabel passwordLabel;
    JLabel repeatPasswordLabel;
    JLabel eMailLabel;
    
    JTextField loginTextField;
    JPasswordField passwordField;
    JPasswordField repeatPassworsField;
    JTextField eMailTextField;
    
    JButton okButton;
    JButton cancelButton;
    
    MigLayout layout;
    RegistrationPaneController controller;
    
    public RegistrationPane() {
        initComponents();
    }
    
    public void initComponents() {
        controller = new RegistrationPaneController();
        controller.setRegistrationPane(this);
        
        layout = new MigLayout("wrap 2", "grow, fill");
        setLayout(layout);
        
        titleLabel = new JLabel("Регистрация");
        loginLabel = new JLabel("Логин");
        passwordLabel = new JLabel("Пароль");
        repeatPasswordLabel = new JLabel("Повторите пароль");
        eMailLabel = new JLabel("e-mail");
        
        loginTextField = new JTextField();
        passwordField = new JPasswordField();
        repeatPassworsField = new JPasswordField();
        eMailTextField = new JFormattedTextField();
        
        okButton = new JButton("Регистрация");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (controller.registration(loginTextField.getText(), 
                        String.copyValueOf(passwordField.getPassword()), eMailTextField.getText())) {
                    controller.closePane();
                    JOptionPane.showMessageDialog(new JFrame(), 
                            "Вы зарегистрированы! Используйте введенные данные для входа", "Успешно", 
                            JOptionPane.DEFAULT_OPTION);
                }
                else
                    JOptionPane.showMessageDialog(new JFrame(), "Введены неверные данные", 
                            "Ошибка!", JOptionPane.DEFAULT_OPTION);

            } 
        });
        cancelButton = new JButton("Отменить");
        
        add(titleLabel, "span 2");
        add(loginLabel);
        add(loginTextField);
        add(passwordLabel);
        add(passwordField);
        add(repeatPasswordLabel);
        add(repeatPassworsField);
        add(eMailLabel);
        add(eMailTextField);
        
        add(cancelButton);
        add(okButton);
        
        doSetupFrame();
    }
    
    public void doSetupFrame() {
        setTitle("Авторизация");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(350, 200);
        setVisible(true);
        //pack();
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        RegistrationPane rp = new RegistrationPane();
    }
}
