package cn.az.code.mvc.dto;

/**
 * @author az
 * @since 09/04/20
 */
public class UserDto extends BaseSearch {

    private String username;

    private String address;

    // region set get

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // endregion
}
