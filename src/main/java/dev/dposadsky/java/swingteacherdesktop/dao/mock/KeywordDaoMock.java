package dev.dposadsky.java.swingteacherdesktop.dao.mock;

import dev.dposadsky.java.swingteacherdesktop.dao.KeywordDao;
import dev.dposadsky.java.swingteacherdesktop.tables.Keyword;

import java.security.Key;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dev.dposadsky.java.swingteacherdesktop.commons.mock.values.KeywordMockValues.*;

public class KeywordDaoMock implements KeywordDao {
    @Override
    public void addKeyword(Keyword keyword) throws SQLException {

    }

    @Override
    public void deleteKeyword(Keyword keyword) throws SQLException {

    }

    @Override
    public void deleteKeyword(int id) throws SQLException {

    }

    @Override
    public Keyword getKeyword(int id) throws SQLException {
        Keyword keyword = new Keyword();
        keyword.setId(ID);
        keyword.setKeywordText(KEYWORD_TEXT);
        return keyword;
    }

    @Override
    public List<Keyword> getAllKeywords() throws SQLException {
        List<Keyword> keywords = new ArrayList<>();
        Keyword keyword = getKeyword(ID);
        keywords.add(keyword);
        return keywords;
    }
}
