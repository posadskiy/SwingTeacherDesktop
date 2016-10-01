package dev.dposadsky.java.swingteacherdesktop.dao.mock;

import dev.dposadsky.java.swingteacherdesktop.dao.TaskDao;
import dev.dposadsky.java.swingteacherdesktop.tables.Task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dev.dposadsky.java.swingteacherdesktop.commons.mock.values.TaskMockValues.*;

public class TaskDaoMock implements TaskDao {

    @Override
    public void addTask(Task task) throws SQLException {

    }

    @Override
    public void deleteTask(Task task) throws SQLException {

    }

    @Override
    public void deleteTask(int id) throws SQLException {

    }

    @Override
    public Task getTask(int id) throws SQLException {
        Task task = new Task();
        task.setId(ID);
        task.setAnswer(ANSWER);
        task.setDifficult(DIFFUCULT);
        task.setIdDocumentation(ID_DOCUMENTATION);
        task.setIdLesson(ID_LESSON);
        task.setImports(IMPORTS);
        task.setQuestion(QUESTION);
        task.setRating(RATING);
        task.setTaskNumber(TASK_NUMBER);
        task.setTitle(TITLE);
        return task;
    }

    @Override
    public List<Task> getTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        Task task = getTask(ID);
        tasks.add(task);
        return tasks;
    }

    @Override
    public List<Task> getTasksByLesson(int lesson) throws SQLException {
        return getTasks();
    }
}
