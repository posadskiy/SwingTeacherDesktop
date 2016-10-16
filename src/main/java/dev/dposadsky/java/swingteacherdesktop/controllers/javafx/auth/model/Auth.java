package dev.dposadsky.java.swingteacherdesktop.controllers.javafx.auth.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Auth {

    private final StringProperty login;
    private final StringProperty password;

    public Auth() {
        this(null, null);
    }

    public Auth(String login, String password) {
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getLogin() {
        return this.login.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getPassword() {
        return this.password.get();
    }

}
