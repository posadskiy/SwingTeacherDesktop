package dev.dposadsky.java.swingteacherdesktop.commons.logger;

public interface Logger {

    public void debug(Class<?> className, String message);
    public void error(Class<?> className, String message);
    public void info(Class<?> className, String message);
    public void trace(Class<?> className, String message);

}
