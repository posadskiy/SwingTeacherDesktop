/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.main;

import dev.dposadsky.java.swingteacherdesktop.controllers.swing.AuthPaneController;
import dev.dposadsky.java.swingteacherdesktop.controllers.swing.MainFrameController;
import dev.dposadsky.java.swingteacherdesktop.controllers.swing.PopupWindowsController;
import dev.dposadsky.java.swingteacherdesktop.controllers.swing.RegistrationPaneController;
import dev.dposadsky.java.swingteacherdesktop.controllers.swing.auth.AuthLoginService;
import dev.dposadsky.java.swingteacherdesktop.controllers.swing.auth.LoginListener;
import dev.dposadsky.java.swingteacherdesktop.dao.CompletedTaskDao;
import dev.dposadsky.java.swingteacherdesktop.dao.DocumentationDao;
import dev.dposadsky.java.swingteacherdesktop.dao.ErrorDao;
import dev.dposadsky.java.swingteacherdesktop.dao.KeywordDao;
import dev.dposadsky.java.swingteacherdesktop.dao.LessonDao;
import dev.dposadsky.java.swingteacherdesktop.dao.ShorthandDao;
import dev.dposadsky.java.swingteacherdesktop.dao.TaskDao;
import dev.dposadsky.java.swingteacherdesktop.dao.UserDao;
import dev.dposadsky.java.swingteacherdesktop.dao.mock.*;
import dev.dposadsky.java.swingteacherdesktop.email.SenderTLS;
import dev.dposadsky.java.swingteacherdesktop.tables.User;
import dev.dposadsky.java.swingteacherdesktop.views.swing.AuthPane;
import dev.dposadsky.java.swingteacherdesktop.views.swing.MainFrameView;
import dev.dposadsky.java.swingteacherdesktop.views.swing.PopupWindowsView;
import dev.dposadsky.java.swingteacherdesktop.views.swing.RegistrationPane;

/**
 *
 * @author DPosadsky
 */
public class Factory {
    
    public static final Factory instance = new Factory();
    
    private Checker checker;
    
    private AuthPaneController authPaneController;
    private RegistrationPaneController registrationPaneController;
    private MainFrameController mainFrameController;
    private PopupWindowsController popupWindowsController;
    
    private AuthPane authPane;
    private RegistrationPane registrationPane;
    private MainFrameView mainFrameView;
    private PopupWindowsView popupWindowsView;
    
    private LoginListener loginListener;
    private AuthLoginService authLoginService;
    
    private LessonDao lessonDao;
    private ShorthandDao shorthandDao;
    private TaskDao taskDao;
    private DocumentationDao documentationDao;
    private ErrorDao errorDao;
    private KeywordDao keywordDao;
    private UserDao userDao;
    private CompletedTaskDao completedTaskDao;
    
    private User currentUser;
    
    private SenderTLS senderTLS;
    
    private Factory() { }
    
    public static Factory getInstance() {
        return Factory.instance;    
    }
    
    public Checker getChecker() {
        if (checker == null) 
            checker = new Checker();
        return checker;
    }
    
    public AuthPaneController getAuthPaneController() {
        if (authPaneController == null)
            authPaneController = new AuthPaneController();
        return authPaneController;
    }
    
    public RegistrationPaneController getRegistrationPaneController() {
        if (registrationPaneController == null)
            registrationPaneController = new RegistrationPaneController();
        return registrationPaneController;
    }
    
    public MainFrameController getMainFrameController() {
        if (mainFrameController == null)
            mainFrameController = new MainFrameController();
        return mainFrameController;
    }
    
    public PopupWindowsController getPopupWindowsController() {
        if (popupWindowsController == null) 
            popupWindowsController = new PopupWindowsController();
        return popupWindowsController;
    }
    
    public AuthPane getAuthPane() {
        if (authPane == null)
            authPane = new AuthPane();
        return authPane;
    }
    
    public RegistrationPane getRegistrationPane() {
        if (registrationPane == null) 
            registrationPane = new RegistrationPane();
        return registrationPane;
    }
    
    public MainFrameView getMainFrameView() {
        if (mainFrameView == null) {
            mainFrameView = new MainFrameView();
            System.out.println(mainFrameView.toString());
        }
        return mainFrameView;
    }
    
    public MainFrameView getMainFrameView(User user) {
        if (mainFrameView == null) {
            mainFrameView = new MainFrameView(user);
        }
        return mainFrameView;
    }
    
    public PopupWindowsView getPopupWindowsView() {
        if (popupWindowsView == null)
            popupWindowsView = new PopupWindowsView();
        return popupWindowsView;
    }
    
    public LoginListener getLoginListener() {
        if (loginListener == null)
            loginListener = new LoginListener();
        return loginListener;
    }
    
    public AuthLoginService getAuthLoginService() {
        if (authLoginService == null) 
            authLoginService = new AuthLoginService();
        return authLoginService;
    }
    
    public LessonDao getLessonDao() {
        if (lessonDao == null) 
            lessonDao = new LessonDaoMock();
        return lessonDao;
    }
    
    public ShorthandDao getShorthandDao() {
        if (shorthandDao == null) 
            shorthandDao = new ShorthandDaoMock();
        return shorthandDao;
    }
    
    public TaskDao getTaskDao() {
        if (taskDao == null) 
            taskDao = new TaskDaoMock();
        return taskDao;
    }
    
    public DocumentationDao getDocumentationDao() {
        if (documentationDao == null) 
            documentationDao = new DocumentationDaoMock();
        return documentationDao;
    }
    
    public ErrorDao getErrorDao() {
        if (errorDao == null) 
            errorDao = new ErrorDaoMock();
        return errorDao;
    }
    
    public KeywordDao getKeywordDao() {
        if (keywordDao == null) 
            keywordDao = new KeywordDaoMock();
        return keywordDao;
    }
    
    public UserDao getUserDao() {
        if (userDao == null) 
            userDao = new UserDaoMock();
        return userDao;
    }
    
    public CompletedTaskDao getCompletedTaskDao() {
        if (completedTaskDao == null) 
            completedTaskDao = new CompletedTaskDaoMock();
        return completedTaskDao;
    }
    
    public User getCurrentUser() {
        if (currentUser == null) 
            currentUser = new User();
        return currentUser;
    }
    
    public void setCurrentUser(User user) {
        currentUser = user;
    }
    
    public SenderTLS getSenderTLS() {
        if (senderTLS == null) 
            senderTLS = new SenderTLS("swingteacherru@gmail.com","{ee78$@oj3X9R2");
        return senderTLS;
    }
    
}
