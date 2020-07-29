package cn.az.code.proxy.service;

/**
 * The interface User service.
 *
 * @author az
 * @since 2020-04-09
 */
public interface UserService {

    /**
     * 新增用户抽象方法
     *
     * @param userName the user name
     * @param password the password
     */
    void addUser(String userName, String password);

    /**
     * 删除用户抽象方法
     *
     * @param userName the user name
     */
    void delUser(String userName);
}
