/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.dao;

import dev.dposadsky.java.swingteacherdesktop.tables.CompletedTask;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DPosadsky
 */
public interface CompletedTaskDao {
    
    public void addCompletedTask(CompletedTask completedTask) throws SQLException;
    public void deleteCompletedTask(CompletedTask completedTask) throws SQLException;
    public void deleteCompletedTask(int id) throws SQLException;
    public CompletedTask getCompletedTask(int id) throws SQLException;
    public List<CompletedTask> getCompletedTaskByUserId(int id) throws SQLException;
    public CompletedTask getCompletedTaskByUserIdByTaskId(int userId, int taskId) throws SQLException;
    public List<CompletedTask> getAllCompletedTask() throws SQLException;
    
}
