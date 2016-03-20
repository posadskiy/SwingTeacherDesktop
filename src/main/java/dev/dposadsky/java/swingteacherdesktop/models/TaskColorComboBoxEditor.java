/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.models;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.event.EventListenerList;

/**
 *
 * @author DPosadsky
 */
public class TaskColorComboBoxEditor implements ComboBoxEditor {
    
    final protected JButton editor;
    
    protected EventListenerList listenerList = new EventListenerList();
    
    public TaskColorComboBoxEditor(Color initialColor) {
        editor = new JButton("");
        editor.setBackground(initialColor);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editor.setBackground(Color.GREEN);
                //fireActionEvent(Color.GREEN);
            }
        };
        editor.addActionListener(actionListener);
    }

    @Override
    public Component getEditorComponent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setItem(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addActionListener(ActionListener al) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeActionListener(ActionListener al) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected void fireActionEvent(Color color) {
    Object listeners[] = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ActionListener.class) {
                ActionEvent actionEvent = new ActionEvent(editor, ActionEvent.ACTION_PERFORMED, color
                    .toString());
                ((ActionListener) listeners[i + 1]).actionPerformed(actionEvent);
            }
        }
    }
    
}
