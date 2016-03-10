/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.controllers;

import dev.dposadsky.java.swingteacherdesktop.dao.CompletedTaskDao;
import dev.dposadsky.java.swingteacherdesktop.dao.DocumentationDao;
import dev.dposadsky.java.swingteacherdesktop.dao.ErrorDao;
import dev.dposadsky.java.swingteacherdesktop.dao.LessonDao;
import dev.dposadsky.java.swingteacherdesktop.dao.TaskDao;
import dev.dposadsky.java.swingteacherdesktop.main.Checker;
import dev.dposadsky.java.swingteacherdesktop.main.Factory;
import dev.dposadsky.java.swingteacherdesktop.tables.CompletedTask;
import dev.dposadsky.java.swingteacherdesktop.tables.Documentation;
import dev.dposadsky.java.swingteacherdesktop.tables.Lesson;
import dev.dposadsky.java.swingteacherdesktop.tables.Task;
import dev.dposadsky.java.swingteacherdesktop.tables.User;
import dev.dposadsky.java.swingteacherdesktop.utils.CheckerResult;
import dev.dposadsky.java.swingteacherdesktop.utils.FileUtils;
import dev.dposadsky.java.swingteacherdesktop.views.MainFrameView;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author DPosadsky
 */
public class MainFrameController {
    
    private Factory factory;
    private TaskDao taskDao;
    private LessonDao lessonDao;
    private DocumentationDao documentationDao;
    private CompletedTaskDao completedTaskDao;
    private ErrorDao errorDao;
    
    public MainFrameController() {
    }
    
    public void startApplication() {
        MainFrameView mainFrameView = new MainFrameView();
    }
    
    public ArrayList<Lesson> getLessonByCategory(int taskCategory) {
        factory = Factory.getInstance();
        lessonDao = factory.getLessonDao();
        ArrayList<Lesson> lessons = null;
        try {
            lessons =  lessonDao.getLessonsByCategory(taskCategory); 
        } catch (SQLException ex) {
            System.out.println("Exception in getLessonByCategory in Controller: " + ex);
        }
        return lessons;
    }
    
    public ArrayList<Task> getTasksByLesson(int lesson) {
        factory = Factory.getInstance();
        taskDao = factory.getTaskDao();
        ArrayList<Task> tasks = null;
        try {
            tasks =  taskDao.getTasksByLesson(lesson); 
        } catch (SQLException ex) {
            System.out.println("Exception in getTasksByLesson in Controller: " + ex);
        }
        return tasks;  
    }
    
    public ArrayList<CompletedTask> getCompletedTaskByUserId(int id) {
        factory = Factory.getInstance();
        completedTaskDao = factory.getCompletedTaskDao();
        ArrayList<CompletedTask> completedTasks = null;
        try {
            completedTasks = completedTaskDao.getCompletedTaskByUserId(id); 
        } catch (SQLException ex) {
            System.out.println("Exception in getCompletedTaskByUserId in Controller: " + ex);
        }
        return completedTasks;
    }
    
    public void addCompletedTask(CompletedTask completedTask) {
        factory = Factory.getInstance();
        completedTaskDao = factory.getCompletedTaskDao();
 
        try {
            completedTaskDao.addCompletedTask(completedTask); 
        } catch (SQLException ex) {
            System.out.println("Exception in getCompletedTaskByUserId in Controller: " + ex);
        }
    }
    
    public Documentation getDocumentation(int i) {
        factory = Factory.getInstance();
        documentationDao = factory.getDocumentationDao();
        Documentation documentation = null;
        try {
            documentation =  documentationDao.getDocumentation(i); 
        } catch (SQLException ex) {
            System.out.println("Exception in getDocumentation in Controller: " + ex);
        }
        return documentation; 
    }
    
    public void loadAndRunClassFromFile(String operators, String imports) {
        String problems = "";
        try {
            problems = FileUtils.isCompileFile(operators, imports);
        } catch (IOException | InstantiationException | IllegalAccessException ex) {
            System.out.println("Error in ActionListener from lookButton: ");
            ex.printStackTrace();
        } 
        if (problems.isEmpty()) {
            try {
                FileUtils.runFile();
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            JOptionPane.showMessageDialog(new JFrame(), problems, "Ошибка компиляции", JOptionPane.DEFAULT_OPTION );
    }
    
    public String isFileCompile(String operators, String imports) {
        String problems = "";
        try {
            problems = FileUtils.isCompileFile(operators, imports);
        } catch (IOException | InstantiationException | IllegalAccessException ex) {
       
        }
        return problems;

    }
    
    public String check(String rightAnswer, String userAnswer) {
        Checker checker = new Checker();
        String errors = ""; //Если здесь null, то при += он превращается в "null"
        ArrayList<CheckerResult> checkerResults = checker.check(rightAnswer, userAnswer);
        for (CheckerResult checkerResult : checkerResults) {
            if (checkerResult.getErrorCode() != 0)
                errors += "Ошибка в компоненте " + checkerResult.getClassName() + " с кодом " + checkerResult.getErrorCode() + "\n";
        }
        return errors;
    }
     
    public User getCurrentUser() {
        factory = Factory.getInstance();
        return factory.getCurrentUser();
    }
}
