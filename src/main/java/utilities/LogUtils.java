package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {
    //Initialize Log4j instance
    private static final Logger LOGGER = LogManager.getLogger(LogUtils.class);

    //Info Level Logs
    public static void info (String message) {
        LOGGER.info(message);
    }
    public static void info (Object object) {
        LOGGER.info(object);
    }

    //Warn Level Logs
    public static void warn (String message) {
        LOGGER.warn(message);
    }
    public static void warn (Object object) {
        LOGGER.warn(object);
    }

    //Error Level Logs
    public static void error (String message) {
        LOGGER.error(message);
    }
    public static void error (Object object) {
        LOGGER.error(object);
    }

    //Fatal Level Logs
    public static void fatal (String message) {
        LOGGER.fatal(message);
    }

    //Debug Level Logs
    public static void debug (String message) {
        LOGGER.debug(message);
    }
    public static void debug (Object object) {
        LOGGER.debug(object);
    }
}
