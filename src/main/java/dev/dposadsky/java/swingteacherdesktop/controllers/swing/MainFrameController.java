package dev.dposadsky.java.swingteacherdesktop.controllers.swing;

import dev.dposadsky.java.swingteacherdesktop.commons.logger.LoggerFactory;
import dev.dposadsky.java.swingteacherdesktop.dao.*;
import dev.dposadsky.java.swingteacherdesktop.main.Checker;
import dev.dposadsky.java.swingteacherdesktop.main.Factory;
import dev.dposadsky.java.swingteacherdesktop.tables.*;
import dev.dposadsky.java.swingteacherdesktop.tables.Error;
import dev.dposadsky.java.swingteacherdesktop.utils.CheckerResult;
import dev.dposadsky.java.swingteacherdesktop.utils.FileUtils;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainFrameController {

    private Factory factory;
    private TaskDao taskDao;
    private LessonDao lessonDao;
    private ShorthandDao shorthandDao;
    private DocumentationDao documentationDao;
    private CompletedTaskDao completedTaskDao;
    private ErrorDao errorDao;
    private KeywordDao keywordDao;

    public MainFrameController() {
    }

    public List<Lesson> getLessonByCategory(int taskCategory) {
        factory = Factory.getInstance();
        lessonDao = factory.getLessonDao();
        List<Lesson> lessons = null;
        try {
            lessons = lessonDao.getLessonsByCategory(taskCategory);
        } catch (SQLException ex) {
            LoggerFactory.error(MainFrameController.class, ex.getMessage());
        }
        return lessons;
    }

    public List<Task> getTasksByLesson(int lesson) {
        factory = Factory.getInstance();
        taskDao = factory.getTaskDao();
        List<Task> tasks = null;
        try {
            tasks = taskDao.getTasksByLesson(lesson);
        } catch (SQLException ex) {
            LoggerFactory.error(MainFrameController.class, ex.getMessage());
        }
        return tasks;
    }

    public CompletedTask getCompletedTaskByUserIdByTaskId(int userId, int taskId) {
        factory = Factory.getInstance();
        completedTaskDao = factory.getCompletedTaskDao();
        CompletedTask completedTask = null;
        try {
            completedTask = completedTaskDao.getCompletedTaskByUserIdByTaskId(userId, taskId);
        } catch (SQLException ex) {
            LoggerFactory.error(MainFrameController.class, ex.getMessage());
        }
        return completedTask;
    }

    public List<CompletedTask> getCompletedTaskByUserId(int id) {
        factory = Factory.getInstance();
        completedTaskDao = factory.getCompletedTaskDao();
        List<CompletedTask> completedTasks = null;
        try {
            completedTasks = completedTaskDao.getCompletedTaskByUserId(id);
        } catch (SQLException ex) {
            LoggerFactory.error(MainFrameController.class, ex.getMessage());
        }
        return completedTasks;
    }

    public void addCompletedTask(CompletedTask completedTask) {
        factory = Factory.getInstance();
        completedTaskDao = factory.getCompletedTaskDao();

        try {
            completedTaskDao.addCompletedTask(completedTask);
        } catch (SQLException ex) {
            LoggerFactory.error(MainFrameController.class, ex.getMessage());
        }
    }

    public Documentation getDocumentation(int i) {
        factory = Factory.getInstance();
        documentationDao = factory.getDocumentationDao();
        Documentation documentation = null;
        try {
            documentation = documentationDao.getDocumentation(i);
        } catch (SQLException ex) {
            LoggerFactory.error(MainFrameController.class, ex.getMessage());
        }
        return documentation;
    }

    public Error getError(int i) {
        factory = Factory.getInstance();
        errorDao = factory.getErrorDao();

        Error error = null;

        try {
            error = errorDao.getError(i);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return error;
    }

    public void loadAndRunClassFromFile(String operators, String imports) {
        String problems = "";
        try {
            problems = FileUtils.isCompileFile(operators, imports);
        } catch (IOException | InstantiationException | IllegalAccessException ex) {
            LoggerFactory.error(MainFrameController.class, ex.getMessage());
        }
        if (problems.isEmpty()) {
            try {
                FileUtils.runFile();
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else
            JOptionPane.showMessageDialog(new JFrame(), problems, "Ошибка компиляции", JOptionPane.DEFAULT_OPTION);
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
        ArrayList<CheckerResult> checkerResults = checker.check(rightAnswer, userAnswer);
        StringBuilder errors = new StringBuilder();
        for (CheckerResult checkerResult : checkerResults) {
            if (checkerResult.getErrorCode() != 0)
                errors.append("Ошибка в компоненте ")
                        .append(checkerResult.getClassName())
                        .append(". ")
                        .append(getError(checkerResult.getErrorCode()).getErrorText())
                        .append("\n");
        }
        return errors.toString();
    }

    public List<Keyword> getKeywords() {
        factory = Factory.getInstance();
        keywordDao = factory.getKeywordDao();

        List<Keyword> keywords = null;

        try {
            keywords = keywordDao.getAllKeywords();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return keywords;
    }

    public List<Shorthand> getShorthand() {
        factory = Factory.getInstance();
        shorthandDao = factory.getShorthandDao();

        List<Shorthand> shorthands = null;

        try {
            shorthands = shorthandDao.getAllShorthands();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return shorthands;
    }

    public CompletionProvider createCompletionProvider() {
        DefaultCompletionProvider provider = new DefaultCompletionProvider();

        List<Keyword> keywords = getKeywords();

        for (Keyword keyword : keywords) {
            provider.addCompletion(new BasicCompletion(provider, keyword.getKeywordText()));
        }

        List<Shorthand> shorthands = getShorthand();

        for (Shorthand shorthand : shorthands) {
            provider.addCompletion(new ShorthandCompletion(provider, shorthand.getShortText(),
                    shorthand.getFullText(), shorthand.getFullText()));
        }

        return provider;
    }
}
