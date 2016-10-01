/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.dao;

import dev.dposadsky.java.swingteacherdesktop.tables.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskDao {
    
    public void addTask(Task task) throws SQLException;
    public void deleteTask(Task task) throws SQLException;
    public void deleteTask(int id) throws SQLException;
    public Task getTask(int id) throws SQLException;
    public List<Task> getTasks() throws SQLException;
    public List<Task> getTasksByLesson(int lesson) throws SQLException;

}
