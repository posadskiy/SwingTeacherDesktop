/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.dao.impl;

import dev.dposadsky.java.swingteacherdesktop.dao.KeywordDao;
import dev.dposadsky.java.swingteacherdesktop.tables.Keyword;
import dev.dposadsky.java.swingteacherdesktop.utils.HibernateUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author DPosadsky
 */
public class KeywordDaoImpl implements KeywordDao {

    @Override
    public void addKeyword(Keyword keyword) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(keyword);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public void deleteKeyword(Keyword keyword) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(keyword);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public void deleteKeyword(int id) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(getKeyword(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public Keyword getKeyword(int id) throws SQLException {
        Keyword keyword = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            keyword = (Keyword) session.load(Keyword.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return keyword;
    }

    @Override
    public ArrayList<Keyword> getAllKeywords() throws SQLException {
        ArrayList<Keyword> keywords = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            keywords = (ArrayList<Keyword>) session.createCriteria(Keyword.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return keywords;
    }

    @Override
    public ArrayList<Keyword> getLessonsByType(int type) throws SQLException {
        ArrayList<Keyword> keywords = null;
        
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            keywords = (ArrayList<Keyword>) session.createCriteria(Keyword.class).add(Restrictions.eq("type", new Integer(type))).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen()))
                session.close();
        }
        
        return keywords;
    }
    
}
