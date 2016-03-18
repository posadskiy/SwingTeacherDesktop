/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.dao.impl;

import dev.dposadsky.java.swingteacherdesktop.dao.ShorthandDao;
import dev.dposadsky.java.swingteacherdesktop.tables.Shorthand;
import dev.dposadsky.java.swingteacherdesktop.utils.HibernateUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import org.hibernate.Session;

/**
 *
 * @author DPosadsky
 */
public class ShorthandDaoImpl implements ShorthandDao {
    
    @Override
    public void addShorthand(Shorthand shorthand) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(shorthand);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public void deleteShorthand(Shorthand shorthand) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(shorthand);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public void deleteShorthand(int id) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(getShorthand(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public Shorthand getShorthand(int id) throws SQLException {
        Shorthand shorthand = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            shorthand = (Shorthand) session.load(Shorthand.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return shorthand;
    }

    @Override
    public ArrayList<Shorthand> getAllShorthands() throws SQLException {
        ArrayList<Shorthand> shorthands = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            shorthands = (ArrayList<Shorthand>) session.createCriteria(Shorthand.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return shorthands;
    }
    
}
