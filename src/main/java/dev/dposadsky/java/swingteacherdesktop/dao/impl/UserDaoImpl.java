/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.dao.impl;

import dev.dposadsky.java.swingteacherdesktop.dao.UserDao;
import dev.dposadsky.java.swingteacherdesktop.tables.User;
import dev.dposadsky.java.swingteacherdesktop.utils.HibernateUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author DPosadsky
 */
public class UserDaoImpl implements UserDao {
    
    @Override
    public void addUser(User user) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(getUserById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User user = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            user = (User) session.load(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return user;
    }

    @Override
    public ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> users = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            users = (ArrayList<User>) session.createCriteria(User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return users;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String pass) throws SQLException {
        List<User> users = null;
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            users = (ArrayList<User>) session.createCriteria(User.class)
                    .add(Restrictions.eq("login", login))
                    .add(Restrictions.eq("password", pass)).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        if (users == null)
            return null;
        
        if (users.isEmpty())
            return null; 
        
        return users.get(0);
    }
    
}
