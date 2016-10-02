package dev.dposadsky.java.swingteacherdesktop.commons.logger;

import org.slf4j.LoggerFactory;

public class LoggerSlf4j implements Logger {

    public void debug(Class<?> className, String message) {
        LoggerFactory.getLogger(className).debug(message);
    }

    public void error(Class<?> className, String message) {
        LoggerFactory.getLogger(className).error(message);
    }

    public void info(Class<?> className, String message) {
        LoggerFactory.getLogger(className).info(message);
    }

    public void trace(Class<?> className, String message) {
        LoggerFactory.getLogger(className).trace(message);
    }

}
