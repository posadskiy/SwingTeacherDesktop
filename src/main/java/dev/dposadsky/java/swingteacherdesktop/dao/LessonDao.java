/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.dao;

import dev.dposadsky.java.swingteacherdesktop.tables.Lesson;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DPosadsky
 */
public interface LessonDao {
    
    public void addLesson(Lesson lesson) throws SQLException;
    public void deleteLesson(Lesson lesson) throws SQLException;
    public void deleteLesson(int id) throws SQLException;
    public Lesson getLesson(int id) throws SQLException;
    public List<Lesson> getAllLessons() throws SQLException;
    public List<Lesson> getLessonsByCategory(int category) throws SQLException;
    
}
