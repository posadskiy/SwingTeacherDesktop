/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.models;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author DPosadsky
 */
public class ComboBoxModel<T> extends DefaultComboBoxModel<T> {
    
    public ComboBoxModel(ArrayList<T> components) {
        super(components.toArray((T[])new Object[components.size()]));
    }
    
    @Override
    public T getSelectedItem() {
        T selectedComponent = (T) super.getSelectedItem();
        return selectedComponent;
    }
    
}
