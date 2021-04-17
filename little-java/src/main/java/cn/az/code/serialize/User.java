package cn.az.code.serialize;

import java.io.Serializable;

/**
 * @author az
 */
public class User implements Serializable {

    private static final long serialVersionUID = -162659387735137664L;

    private String username;
    private String desc;

    public User(String username, String desc) {
        this.username = username;
        this.desc = desc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
