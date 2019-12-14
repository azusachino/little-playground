package cn.az.code.learn;

/**
 * @author azusachino
 * @version 12/12/2019
 */
public class Add {

    public static void main(String[] args) {
        Runnable r = System::currentTimeMillis;
        Adder add = Integer::sum;
        System.out.println(add.add(2,3));
        r.run();
    }
}
