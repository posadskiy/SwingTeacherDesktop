/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.dao;

import dev.dposadsky.java.swingteacherdesktop.tables.Error;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DPosadsky
 */
public interface ErrorDao {
    
    public void addError(Error error) throws SQLException;
    public void deleteError(Error error) throws SQLException;
    public void deleteError(int id) throws SQLException;
    public Error getError(int id) throws SQLException;
    public List<Error> getAllErrors() throws SQLException;
    
}
