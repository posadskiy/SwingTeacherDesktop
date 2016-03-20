/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.models;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class ComplexCellRenderer implements ListCellRenderer {
    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index,
        boolean isSelected, boolean cellHasFocus) {
        String theText = null;
        Color theForeground = null;
        JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
            isSelected, cellHasFocus);

        if (value instanceof Object[]) {
            Object values[] = (Object[]) value;
            theText = (String) values[0];
            theForeground = (Color) values[1];
        } else {
            theForeground = list.getForeground();
            theText = "";
        }
        //if (!isSelected) {
        renderer.setForeground(theForeground);
        renderer.setBackground(theForeground);
        //}
      
        renderer.setText(theText);
        //renderer.setFont(theFont);
        
        return renderer;
    }
    
    
}
/*
public class ComplexRenderingSample {
    public static void main(String args[]) {
        Object elements[][] = {
            { Color.RED },
            { Color.BLUE },
        };

        JFrame frame = new JFrame("Complex Renderer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ListCellRenderer renderer = new ComplexCellRenderer();
        
        JComboBox comboBox = new JComboBox(elements);
        comboBox.setRenderer(renderer);
        
        elements[0][0] = Color.GREEN;


        frame.add(comboBox, BorderLayout.NORTH);

        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
*/