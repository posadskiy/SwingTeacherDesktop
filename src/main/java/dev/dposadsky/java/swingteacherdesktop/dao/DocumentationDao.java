/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.dao;

import dev.dposadsky.java.swingteacherdesktop.tables.Documentation;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DPosadsky
 */
public interface DocumentationDao {
    
    public void addDocumentation(Documentation documentation) throws SQLException;
    public void deleteDocumentation(Documentation documentation) throws SQLException;
    public void deleteDocumentation(int id) throws SQLException;
    public Documentation getDocumentation(int id) throws SQLException;
    public List<Documentation> getAllDocumentation() throws SQLException;
    
}
