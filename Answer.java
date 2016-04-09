package dev.dposadsky.java.swingteacherdesktop.utils;
import javax.swing.*;
import java.awt.Dimension;

public class Answer extends CreateFrame {
 public Answer() {
setTitle("ׂגמי פנויל");
JButton button = new JButton();
JButton button1 = new JButton();
button1.setText("");
button1.setSize(100,100);
button.setIcon(null);
setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
setPreferredSize(new Dimension(700, 400));
pack();
setLocationRelativeTo(null);
setVisible(true);
}}