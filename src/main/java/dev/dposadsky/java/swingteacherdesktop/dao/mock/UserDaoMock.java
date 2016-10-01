package dev.dposadsky.java.swingteacherdesktop.dao.mock;

import dev.dposadsky.java.swingteacherdesktop.dao.UserDao;
import dev.dposadsky.java.swingteacherdesktop.tables.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dev.dposadsky.java.swingteacherdesktop.commons.mock.values.UserMockValues.*;

public class UserDaoMock implements UserDao {

    @Override
    public void addUser(User user) throws SQLException {

    }

    @Override
    public void deleteUser(User user) throws SQLException {

    }

    @Override
    public void deleteUser(int id) throws SQLException {

    }

    @Override
    public User getUserById(int id) throws SQLException {
        User user = new User();
        user.setCompleteTraining(COMPLETE_TRAINING);
        user.setEmail(EMAIL);
        user.setId(ID);
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        return user;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String pass) throws SQLException {
        return getUserById(0);
    }

    @Override
    public List<User> getUsers() throws SQLException {
        List users = new ArrayList<User>();
        User user = getUserById(0);
        users.add(user);
        return users;
    }
}
