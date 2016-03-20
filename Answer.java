package dev.dposadsky.java.swingteacherdesktop.utils;
import javax.swing.*;
import java.awt.Dimension;
 public class Answer extends CreateFrame {
 public Answer() {
for (int i = 0; i < 10; i++) {
	System.out.println ("Dringwoter");	
} setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
setPreferredSize(new Dimension(700, 400));
pack();
setLocationRelativeTo(null);
setVisible(true);
}}