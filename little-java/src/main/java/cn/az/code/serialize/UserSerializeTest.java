package cn.az.code.serialize;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @author az
 */
public class UserSerializeTest {

    /**
     * 看 java.io.ObjectStreamClass#writeNonProxy ，如果当前类(User)没有定义 serialVersionUID，
     * 就会调用java.io.ObjectStreamClass#computeDefaultSUID生成默认的序列化唯一标示
     * 根据类名, 方法和属性等参数生成的 hash 值
     */
    public static void main(String[] args) throws Exception {
//        User user = new User("abc");
//        FileOutputStream fo = new FileOutputStream("user.bytes");
//        ObjectOutputStream so = new ObjectOutputStream(fo);
//        so.writeObject(user);
//        so.close();
        FileInputStream fi = new FileInputStream("user.bytes");
        ObjectInputStream si = new ObjectInputStream(fi);
        User user = (User) si.readObject();
        System.out.println(user);
        si.close();

    }

}
