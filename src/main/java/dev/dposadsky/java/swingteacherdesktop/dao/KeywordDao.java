/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.dao;

import dev.dposadsky.java.swingteacherdesktop.tables.Keyword;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DPosadsky
 */
public interface KeywordDao {
    
    public void addKeyword(Keyword keyword) throws SQLException;
    public void deleteKeyword(Keyword keyword) throws SQLException;
    public void deleteKeyword(int id) throws SQLException;
    public Keyword getKeyword(int id) throws SQLException;
    public List<Keyword> getAllKeywords() throws SQLException;
    
}
