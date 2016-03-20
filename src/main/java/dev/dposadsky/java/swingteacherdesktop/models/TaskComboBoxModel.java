/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.models;

import dev.dposadsky.java.swingteacherdesktop.tables.Task;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author DPosadsky
 */
public class TaskComboBoxModel extends AbstractListModel implements ComboBoxModel {

    ArrayList<Task> tasks = null;
    
    Task selection = null;
    
    public TaskComboBoxModel() {
        super();
    }
    
    public TaskComboBoxModel(ArrayList<Task> components) {
        super();
        tasks = components;
    }
    
    @Override
    public int getSize() {
        if (tasks == null) 
            return 0;
        return tasks.size();
    }

    @Override
    public Object getElementAt(int i) {
        if (tasks == null) 
            return null;
        return tasks.get(i);
    }

    @Override
    public void setSelectedItem(Object o) {
        selection = (Task) o;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
    
}
