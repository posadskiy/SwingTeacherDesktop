package dev.dposadsky.java.swingteacherdesktop.dao.mock;

import dev.dposadsky.java.swingteacherdesktop.dao.DocumentationDao;
import dev.dposadsky.java.swingteacherdesktop.tables.Documentation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dev.dposadsky.java.swingteacherdesktop.commons.mock.values.DocumentationMockValues.*;

public class DocumentationDaoMock implements DocumentationDao {


    @Override
    public void addDocumentation(Documentation documentation) throws SQLException {

    }

    @Override
    public void deleteDocumentation(Documentation documentation) throws SQLException {

    }

    @Override
    public void deleteDocumentation(int id) throws SQLException {

    }

    @Override
    public Documentation getDocumentation(int id) throws SQLException {
        Documentation documentation = new Documentation();
        documentation.setId(ID);
        documentation.setText(TEXT);
        return documentation;
    }

    @Override
    public List<Documentation> getAllDocumentation() throws SQLException {
        List<Documentation> documentations = new ArrayList<>();
        Documentation documentation = getDocumentation(0);
        documentations.add(documentation);
        return documentations;
    }
}
