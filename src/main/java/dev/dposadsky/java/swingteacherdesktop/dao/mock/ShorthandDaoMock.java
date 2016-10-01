package dev.dposadsky.java.swingteacherdesktop.dao.mock;

import dev.dposadsky.java.swingteacherdesktop.dao.ShorthandDao;
import dev.dposadsky.java.swingteacherdesktop.tables.Shorthand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dev.dposadsky.java.swingteacherdesktop.commons.mock.values.ShorthandMockValues.*;

public class ShorthandDaoMock implements ShorthandDao {
    @Override
    public void addShorthand(Shorthand shorthand) throws SQLException {

    }

    @Override
    public void deleteShorthand(Shorthand shorthand) throws SQLException {

    }

    @Override
    public void deleteShorthand(int id) throws SQLException {

    }

    @Override
    public Shorthand getShorthand(int id) throws SQLException {
        Shorthand shorthand = new Shorthand();
        shorthand.setId(ID);
        shorthand.setShortText(SHORT_TEXT);
        shorthand.setFullText(FULL_TEXT);
        return shorthand;
    }

    @Override
    public List<Shorthand> getAllShorthands() throws SQLException {
        List<Shorthand> shorthands = new ArrayList<>();
        Shorthand shorthand = getShorthand(ID);
        shorthands.add(shorthand);
        return shorthands;
    }
}
