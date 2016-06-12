/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.dao.impl;

import dev.dposadsky.java.swingteacherdesktop.dao.CompletedTaskDao;
import dev.dposadsky.java.swingteacherdesktop.tables.CompletedTask;
import dev.dposadsky.java.swingteacherdesktop.utils.HibernateUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author DPosadsky
 */
public class CompletedTaskDaoImpl implements CompletedTaskDao {

    @Override
    public void addCompletedTask(CompletedTask completedTask) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(completedTask);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }    
    }

    @Override
    public void deleteCompletedTask(CompletedTask completedTask) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(completedTask);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public void deleteCompletedTask(int id) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(getCompletedTask(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public CompletedTask getCompletedTask(int id) throws SQLException {
        CompletedTask completedTask = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            completedTask = (CompletedTask) session.load(CompletedTask.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return completedTask;
    }

    @Override
    public ArrayList<CompletedTask> getCompletedTaskByUserId(int id) throws SQLException {
        ArrayList<CompletedTask> completedTasks = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            completedTasks = (ArrayList<CompletedTask>) session.createCriteria(CompletedTask.class)
                    .add( Restrictions.eq("userId", Integer.valueOf(id)) ).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return completedTasks;
    }
    
    @Override
    public CompletedTask getCompletedTaskByUserIdByTaskId(int userId, int taskId) throws SQLException {
        ArrayList<CompletedTask> completedTasks = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            completedTasks = (ArrayList<CompletedTask>) session.createCriteria(CompletedTask.class)
                    .add( Restrictions.eq("userId", userId) )
                    .add( Restrictions.eq("taskId", taskId) ).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return (!completedTasks.isEmpty()) ? completedTasks.get(0) : null;
    }

    @Override
    public ArrayList<CompletedTask> getAllCompletedTask() throws SQLException {
        ArrayList<CompletedTask> completedTasks = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            completedTasks = (ArrayList<CompletedTask>) session.createCriteria(CompletedTask.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return completedTasks;
    }
  
}
