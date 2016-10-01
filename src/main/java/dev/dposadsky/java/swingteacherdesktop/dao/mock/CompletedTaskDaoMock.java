package dev.dposadsky.java.swingteacherdesktop.dao.mock;

import dev.dposadsky.java.swingteacherdesktop.commons.mock.values.TaskMockValues;
import dev.dposadsky.java.swingteacherdesktop.commons.mock.values.UserMockValues;
import dev.dposadsky.java.swingteacherdesktop.dao.CompletedTaskDao;
import dev.dposadsky.java.swingteacherdesktop.tables.CompletedTask;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dev.dposadsky.java.swingteacherdesktop.commons.mock.values.CompletedTaskMockValues.*;

public class CompletedTaskDaoMock implements CompletedTaskDao {
    @Override
    public void addCompletedTask(CompletedTask completedTask) throws SQLException {

    }

    @Override
    public void deleteCompletedTask(CompletedTask completedTask) throws SQLException {

    }

    @Override
    public void deleteCompletedTask(int id) throws SQLException {

    }

    @Override
    public CompletedTask getCompletedTask(int id) throws SQLException {
        CompletedTask completedTask = new CompletedTask();
        completedTask.setId(ID);
        completedTask.setTaskId(TaskMockValues.ID);
        completedTask.setUserId(UserMockValues.ID);
        return completedTask;
    }

    @Override
    public List<CompletedTask> getCompletedTaskByUserId(int id) throws SQLException {
        return getAllCompletedTask();
    }

    @Override
    public CompletedTask getCompletedTaskByUserIdByTaskId(int userId, int taskId) throws SQLException {
        return getCompletedTask(ID);
    }

    @Override
    public List<CompletedTask> getAllCompletedTask() throws SQLException {
        List<CompletedTask> completedTasks = new ArrayList<>();
        CompletedTask completedTask = getCompletedTask(ID);
        completedTasks.add(completedTask);
        return completedTasks;
    }
}
