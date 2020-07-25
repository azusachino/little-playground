package cn.az.code.structure;

import java.util.Vector;
import java.util.stream.Stream;

/**
 * 在使用同步容器的时候， 如果涉及到多个线程同时执行删除操作， 要考虑是否加锁
 *
 * @author az
 * @date 2020/3/22
 */
public class VectorDemo {

    public static void main(String[] args) {
        Vector<Integer> v = new Vector<>();
        Stream.iterate(1, i -> i + 1).limit(10).forEach(v::add);
        // Stream.iterate(1, i -> i + 1).limit(5).forEach(i -> deleteLast(v)); // ok

        /**
         * Exception in thread "Thread-10" Exception in thread "Thread-11" Exception in thread "Thread-12" Exception in thread "Thread-13" Exception in thread "Thread-14" java.lang.ArrayIndexOutOfBoundsException: -1
         * 	at java.util.Vector.elementData(Vector.java:737)
         * 	at java.util.Vector.remove(Vector.java:835)
         * 	at cn.az.code.structure.VectorDemo.deleteLast(VectorDemo.java:28)
         * 	at cn.az.code.structure.VectorDemo.lambda$main$1(VectorDemo.java:19)
         * 	at java.lang.Thread.run(Thread.java:748)
         */
        for (int i = 0; i < 15; i++) {
            new Thread(() -> {
                deleteLast(v);
            }).start();
        }
        System.out.println(v);
    }

    static void deleteLast(Vector<?> v) {

        int lastIndex = v.size() - 1;
        v.remove(lastIndex);
    }

    static void deleteLast2(Vector<?> v) {
        synchronized (v) {
            int lastIndex = v.size() - 1;
            v.remove(lastIndex);
        }
    }

}
