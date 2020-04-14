package cn.az.code.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Optional;

/**
 * @author az
 * @since 2020-04-14
 */
public class GsonUtil {

    private GsonUtil() {
    }

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .setLenient()
            .create();


    public static <T> Optional<String> toJson(T t) {
        try {
            return Optional.of(GSON.toJson(t));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
