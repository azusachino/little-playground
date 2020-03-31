package cn.az.code.gson;

import com.google.gson.annotations.SerializedName;

/**
 * @author azusachino
 * @version 12/20/2019
 */
public class User {
    @SerializedName("ID")
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
