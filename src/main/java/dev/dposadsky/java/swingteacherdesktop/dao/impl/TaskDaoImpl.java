/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.dao.impl;

import dev.dposadsky.java.swingteacherdesktop.dao.TaskDao;
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
public class TaskDaoImpl implements TaskDao {

    @Override
    public void addTask(Task task) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public void deleteTask(Task task) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public void deleteTask(int id) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(getTask(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public Task getTask(int id) throws SQLException {
        Task task = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            task = (Task) session.load(Task.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return task;
    }

    @Override
    public ArrayList<Task> getTasks() throws SQLException {
        ArrayList<Task> tasks = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tasks = (ArrayList<Task>) session.createCriteria(Task.class)
                    .addOrder(Order.asc("taskNumber")).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return tasks;
    }

    @Override
    public ArrayList<Task> getTasksByLesson(int lesson) throws SQLException {
        ArrayList<Task> tasks = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            tasks = (ArrayList<Task>) session.createCriteria(Task.class)
                    .add(Restrictions.eq("idLesson", lesson))
                    .addOrder(Order.asc("taskNumber")).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return tasks;
    }
    
}
