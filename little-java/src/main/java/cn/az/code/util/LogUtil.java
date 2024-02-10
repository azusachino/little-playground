package cn.az.code.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author az
 * @since 5/27/2021 23:07
 */
public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger("LogUtil");

    public static void debug(String s, Object... param) {
        logger.debug(s, param);
    }

    public static void info(String s, Object... param) {
        logger.info(s, param);
    }

    public static void warn(String s, Object... param) {
        logger.warn(s, param);
    }

    public static void error(String s, Object... param) {
        logger.error(s, param);
    }

    public static void error(String s, Throwable t) {
        logger.error(s, t);
    }
}
