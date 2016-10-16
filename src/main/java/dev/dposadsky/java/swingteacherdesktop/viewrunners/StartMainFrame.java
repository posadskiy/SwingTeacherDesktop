/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.viewrunners;

import dev.dposadsky.java.swingteacherdesktop.main.Factory;
import dev.dposadsky.java.swingteacherdesktop.tables.User;
import dev.dposadsky.java.swingteacherdesktop.utils.StringUtils;
import dev.dposadsky.java.swingteacherdesktop.views.swing.MainFrameView;

public class StartMainFrame {

    public static void main(String[] args) {
        User user = new User();
        user.setEmail("dmitry.posadsky@gmail.com");
        user.setLastLogin(1);
        user.setLogin("admin");
        user.setPassword(StringUtils.md5Apache("25531094"));
        user.setId(1);
        Factory factory = Factory.getInstance();
        MainFrameView mfv = factory.getMainFrameView(user);
    }

}
