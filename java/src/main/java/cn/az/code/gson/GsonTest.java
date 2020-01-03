package cn.az.code.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Field;

/**
 * @author azusachino
 * @version 12/20/2019
 */
public class GsonTest {

    public static void main(String[] args) {
        String u = "{ID:123,name:az}";
        Gson gson = new Gson();
        User user = gson.fromJson(u,User.class);
        new GsonBuilder().setFieldNamingStrategy(Field::toGenericString).create();
        System.out.println(user.getId() + "," + user.getName());
    }
}
