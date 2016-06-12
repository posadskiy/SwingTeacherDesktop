/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.views;

import dev.dposadsky.java.swingteacherdesktop.controllers.PopupWindowsController;
import dev.dposadsky.java.swingteacherdesktop.controllers.RegistrationPaneController;
import dev.dposadsky.java.swingteacherdesktop.main.Factory;
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
    JLabel passwordRepeatLabel;
    JLabel eMailLabel;
    
    JTextField loginTextField;
    JPasswordField passwordField;
    JPasswordField passwordRepeatField;
    JTextField eMailTextField;
    
    JButton okButton;
    JButton cancelButton;
    
    MigLayout layout;
    RegistrationPaneController registrationPaneController;
    PopupWindowsController popupWindowsController;
    
    public RegistrationPane() {
        initComponents();
    }
    
    public void initComponents() {
        Factory factory = Factory.getInstance();
        registrationPaneController = factory.getRegistrationPaneController();
        popupWindowsController = factory.getPopupWindowsController();
        
        layout = new MigLayout("wrap 2", "grow, fill");
        setLayout(layout);
        
        titleLabel = new JLabel("Регистрация");
        loginLabel = new JLabel("Логин");
        passwordLabel = new JLabel("Пароль");
        passwordRepeatLabel = new JLabel("Повторите пароль");
        eMailLabel = new JLabel("e-mail");
        
        loginTextField = new JTextField();
        passwordField = new JPasswordField();
        passwordRepeatField = new JPasswordField();
        eMailTextField = new JFormattedTextField();
        
        okButton = new JButton("Регистрация");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                registrationButtonClick();
            } 
        });
        cancelButton = new JButton("Отменить");
        
        add(titleLabel, "span 2");
        add(loginLabel);
        add(loginTextField);
        add(passwordLabel);
        add(passwordField);
        add(passwordRepeatLabel);
        add(passwordRepeatField);
        add(eMailLabel);
        add(eMailTextField);
        
        add(cancelButton);
        add(okButton);
        
        doSetupFrame();
    }
    
    public void doSetupFrame() {
        setTitle("Регистрация");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(350, 200);
        setVisible(true);
        //pack();
        setLocationRelativeTo(null);
    }
    
    public void registrationButtonClick() {
        if (registrationPaneController.registration(loginTextField.getText(), 
                String.copyValueOf(passwordField.getPassword()), 
                String.copyValueOf(passwordRepeatField.getPassword()), 
                eMailTextField.getText())) {
            registrationPaneController.closePane();
            popupWindowsController.createPopupWindow(
                    "Вы зарегистрированы! Используйте введенные данные для входа", "Успешно");
        }
        else
            popupWindowsController.createPopupWindow("Введены неверные данные", "Ошибка!");
    }
    
    public JTextField getLoginTextField() {
        return loginTextField;
    }
    
    public JPasswordField getPasswordField() {
        return passwordField;
    }
    
    public JPasswordField getPasswordRepeatField() {
        return passwordRepeatField;
    }
    
    public JTextField getEMailTextField() {
        return eMailTextField;
    }
    
    public static void main(String[] args) {
        Factory factory = Factory.getInstance();
        RegistrationPane pane = factory.getRegistrationPane();
    }
}
