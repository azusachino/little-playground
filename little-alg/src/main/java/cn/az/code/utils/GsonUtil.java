package cn.az.code.utils;

import com.google.gson.Gson;

public class GsonUtil {
    
    private static final Gson GSON = new Gson();

    public static void print(Object obj) {
        System.out.println(GSON.toJson(obj));
    }
}
