package dev.dposadsky.java.swingteacherdesktop.dao.mock;

import dev.dposadsky.java.swingteacherdesktop.dao.ErrorDao;
import dev.dposadsky.java.swingteacherdesktop.tables.Error;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dev.dposadsky.java.swingteacherdesktop.commons.mock.values.ErrorMockValues.*;

public class ErrorDaoMock implements ErrorDao {
    @Override
    public void addError(Error error) throws SQLException {

    }

    @Override
    public void deleteError(Error error) throws SQLException {

    }

    @Override
    public void deleteError(int id) throws SQLException {

    }

    @Override
    public Error getError(int id) throws SQLException {
        Error error = new Error();
        error.setId(ID);
        error.setErrorText(ERROR_TEXT);
        return error;
    }

    @Override
    public List<Error> getAllErrors() throws SQLException {
        List<Error> errors = new ArrayList<>();
        Error error = getError(ID);
        errors.add(error);
        return errors;
    }
}
