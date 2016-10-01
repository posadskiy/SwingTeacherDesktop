/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.dao;

import dev.dposadsky.java.swingteacherdesktop.tables.Shorthand;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DPosadsky
 */
public interface ShorthandDao {
    
    public void addShorthand(Shorthand shorthand) throws SQLException;
    public void deleteShorthand(Shorthand shorthand) throws SQLException;
    public void deleteShorthand(int id) throws SQLException;
    public Shorthand getShorthand(int id) throws SQLException;
    public List<Shorthand> getAllShorthands() throws SQLException;
    
}
