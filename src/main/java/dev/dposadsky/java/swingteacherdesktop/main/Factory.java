/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.main;

import dev.dposadsky.java.swingteacherdesktop.dao.CompletedTaskDao;
import dev.dposadsky.java.swingteacherdesktop.dao.DocumentationDao;
import dev.dposadsky.java.swingteacherdesktop.dao.ErrorDao;
import dev.dposadsky.java.swingteacherdesktop.dao.LessonDao;
import dev.dposadsky.java.swingteacherdesktop.dao.TaskDao;
import dev.dposadsky.java.swingteacherdesktop.dao.UserDao;
import dev.dposadsky.java.swingteacherdesktop.dao.impl.CompletedTaskDaoImpl;
import dev.dposadsky.java.swingteacherdesktop.dao.impl.DocumentationDaoImpl;
import dev.dposadsky.java.swingteacherdesktop.dao.impl.ErrorDaoImpl;
import dev.dposadsky.java.swingteacherdesktop.dao.impl.LessonDaoImpl;
import dev.dposadsky.java.swingteacherdesktop.dao.impl.TaskDaoImpl;
import dev.dposadsky.java.swingteacherdesktop.dao.impl.UserDaoImpl;
import dev.dposadsky.java.swingteacherdesktop.tables.User;

/**
 *
 * @author DPosadsky
 */
public class Factory {
    
    public static Factory instance = new Factory();
    public LessonDao lessonDao;
    public TaskDao taskDao;
    public DocumentationDao documentationDao;
    public ErrorDao errorDao;
    public UserDao userDao;
    public CompletedTaskDao completedTaskDao;
    
    public User currentUser;
    
    private Factory() { }
    
    public static Factory getInstance() {
        return Factory.instance;    
    }
    
    public LessonDao getLessonDao() {
        if (lessonDao == null) 
            lessonDao = new LessonDaoImpl();
        return lessonDao;
    }
    
    public TaskDao getTaskDao() {
        if (taskDao == null) 
            taskDao = new TaskDaoImpl();
        return taskDao;
    }
    
    public DocumentationDao getDocumentationDao() {
        if (documentationDao == null) 
            documentationDao = new DocumentationDaoImpl();
        return documentationDao;
    }
    
    public ErrorDao getErrorDao() {
        if (errorDao == null) 
            errorDao = new ErrorDaoImpl();
        return errorDao;
    }
    
    public UserDao getUserDao() {
        if (userDao == null) 
            userDao = new UserDaoImpl();
        return userDao;
    }
    
    public CompletedTaskDao getCompletedTaskDao() {
        if (completedTaskDao == null) 
            completedTaskDao = new CompletedTaskDaoImpl();
        return completedTaskDao;
    }
    
    public User getCurrentUser() {
        if (currentUser == null) 
            currentUser = new User();
        return currentUser;
    }
    
    public void serCurrentUser(User user) {
        currentUser = user;
    }
}
