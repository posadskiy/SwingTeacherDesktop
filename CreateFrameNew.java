package dev.dposadsky.java.swingteacherdesktop.utils;
import javax.swing.*;
import java.awt.Dimension;
public class CreateFrameNew extends CreateFrame {
 public JFrame createFrame() {
JFrame frame = new JFrame("You Frame");frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
frame.setPreferredSize(new Dimension(700, 400));
frame.pack();
frame.setLocationRelativeTo(null);
frame.setVisible(true);
return frame; } }
