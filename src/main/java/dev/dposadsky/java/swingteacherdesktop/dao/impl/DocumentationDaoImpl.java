/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.dao.impl;

import dev.dposadsky.java.swingteacherdesktop.dao.DocumentationDao;
import dev.dposadsky.java.swingteacherdesktop.tables.Documentation;
import dev.dposadsky.java.swingteacherdesktop.utils.HibernateUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import org.hibernate.Session;

/**
 *
 * @author DPosadsky
 */
public class DocumentationDaoImpl implements DocumentationDao{

    @Override
    public void addDocumentation(Documentation documentation) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(documentation);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public void deleteDocumentation(Documentation documentation) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(documentation);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public void deleteDocumentation(int id) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(getDocumentation(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public Documentation getDocumentation(int id) throws SQLException {
        Documentation documentation = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            documentation = (Documentation) session.load(Documentation.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return documentation;
    }

    @Override
    public ArrayList<Documentation> getAllDocumentation() throws SQLException {
        ArrayList<Documentation> documentation = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            documentation = (ArrayList<Documentation>) session.createCriteria(Documentation.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return documentation;
    }
    
}
