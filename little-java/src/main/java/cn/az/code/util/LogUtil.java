package cn.az.code.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author az
 * @since 5/27/2021 23:07
 */
public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger("LogUtil");

    public static void info(String s) {
        logger.info(s);
    }

    public static void warn(String s) {
        logger.warn(s);
    }

    public static void error(String s) {
        logger.error(s);
    }

    public static void error(String s, Throwable t) {
        logger.error(s, t);
    }
}
