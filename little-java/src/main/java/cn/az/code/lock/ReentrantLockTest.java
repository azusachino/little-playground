package cn.az.code.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Test
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see ReentrantLock
 * @since 2020-03-16
 */
public class ReentrantLockTest {

    private static final ReentrantLock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        synchronizedFunction(ReentrantLockTest::action1);
    }

    private static void action1() {
        synchronizedFunction(ReentrantLockTest::action2);
    }

    private static void action2() {
        System.out.println("Hello, World");
    }

    public static void synchronizedFunction(Runnable r) {
        LOCK.lock();
        try {
            r.run();
        } finally {
            LOCK.unlock();
        }
    }
}
