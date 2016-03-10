/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.dao;

import dev.dposadsky.java.swingteacherdesktop.tables.Task;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DPosadsky
 */
public interface TaskDao {
    
    public void addTask(Task task) throws SQLException;
    public void deleteTask(Task task) throws SQLException;
    public void deleteTask(int id) throws SQLException;
    public Task getTask(int id) throws SQLException;
    public ArrayList<Task> getTasks() throws SQLException;
    public ArrayList<Task> getTasksByLesson(int lesson) throws SQLException;
}
