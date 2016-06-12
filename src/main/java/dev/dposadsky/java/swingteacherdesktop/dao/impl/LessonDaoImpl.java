/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.dao.impl;

import dev.dposadsky.java.swingteacherdesktop.dao.LessonDao;
import dev.dposadsky.java.swingteacherdesktop.tables.Lesson;
import dev.dposadsky.java.swingteacherdesktop.tables.Task;
import dev.dposadsky.java.swingteacherdesktop.utils.HibernateUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author DPosadsky
 */
public class LessonDaoImpl implements LessonDao {

    @Override
    public void addLesson(Lesson lesson) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(lesson);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public void deleteLesson(Lesson lesson) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(lesson);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public void deleteLesson(int id) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(getLesson(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public Lesson getLesson(int id) throws SQLException {
        Lesson lesson = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            lesson = (Lesson) session.load(Lesson.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return lesson;
    }

    @Override
    public ArrayList<Lesson> getAllLessons() throws SQLException {
        ArrayList<Lesson> lessons = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            lessons = (ArrayList<Lesson>) session.createCriteria(Lesson.class).addOrder(Order.asc("lessonNumber")).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return lessons;
    }

    @Override
    public ArrayList<Lesson> getLessonsByCategory(int category) throws SQLException {
        ArrayList<Lesson> lessons = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            lessons = (ArrayList<Lesson>) session.createCriteria(Lesson.class)
                    .add( Restrictions.eq("idTaskCategory", category) )
                    .addOrder(Order.asc("lessonNumber")).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return lessons;
    }
    
    
    
}
