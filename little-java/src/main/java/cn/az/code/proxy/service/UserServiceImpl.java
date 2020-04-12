package cn.az.code.proxy.service;

/**
 * @author az
 * @since 2020-04-09
 */
public class UserServiceImpl implements UserService {
    /**
     * 新增用户抽象方法
     *
     * @param userName the user name
     * @param password the password
     */
    @Override
    public void addUser(String userName, String password) {
        System.out.println("调用了新增的方法！");
        System.out.println("传入参数为 userName: " + userName + " password: " + password);
    }

    /**
     * 删除用户抽象方法
     *
     * @param userName the user name
     */
    @Override
    public void delUser(String userName) {
        System.out.println("调用了删除的方法！");
        System.out.println("传入参数为 userName: " + userName);
    }
}
