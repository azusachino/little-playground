package cn.az.code.learn;

/**
 * @author azusachino
 * @version 12/12/2019
 */
public class Add {

    public static void main(String[] args) {
        Runnable r = System::currentTimeMillis;
        Adder<Integer> add = Integer::sum;
        Adder<String> a = Add::add;
        System.out.println(add.add(2,3));
        System.out.println(a.add("2","3"));
        r.run();
    }

    private static String add(String a, String b) {
        return a+b;
    }
}
