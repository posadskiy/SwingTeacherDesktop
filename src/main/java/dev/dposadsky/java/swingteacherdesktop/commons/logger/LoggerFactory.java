package dev.dposadsky.java.swingteacherdesktop.commons.logger;

public class LoggerFactory {

    private static Logger logger = new LoggerSlf4j();

    public static void debug(Class<?> className, String message) {
        logger.debug(className, message);
    }

    public static void error(Class<?> className, String message) {
        logger.error(className, message);
    }

    public static void info(Class<?> className, String message) {
        logger.info(className, message);
    }

    public static void trace(Class<?> className, String message) {
        logger.trace(className, message);
    }

}
