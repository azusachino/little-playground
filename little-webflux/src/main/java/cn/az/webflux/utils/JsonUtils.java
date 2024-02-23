package cn.az.webflux.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper OM = new ObjectMapper();

    static {
        OM.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private JsonUtils() {
    }

    public static <T> String toJson(T t) {
        try {
            return OM.writeValueAsString(t);
        } catch (Exception e) {
            LOGGER.error("toJson error", e);
        }
        return null;
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return OM.readValue(json, clazz);
        } catch (Exception e) {
            LOGGER.error("toJson error", e);
        }
        return null;
    }
}
