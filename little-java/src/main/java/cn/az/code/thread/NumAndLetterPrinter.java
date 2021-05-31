package cn.az.code.thread;

/**
 * @author az
 * @since 11/15/20
 */
public class NumAndLetterPrinter {

    static final Object LOCK = new Object();
    // private static final char C = 'A';
    // private static final int I = 0;

    public static void main(String[] args) {
        new Thread(() -> printer(), "numThread").start();
        new Thread(() -> printer(), "letterThread").start();
    }

    private static void printer() {
        synchronized (LOCK) {
            for (int i = 0; i < 26; i++) {
                if ("numThread".equals(Thread.currentThread().getName())) {
                    // 打印数字1-26
                    System.out.print((i + 1));
                    // 唤醒其他在等待的线程
                    LOCK.notifyAll();
                    try {
                        // 让当前线程释放锁资源，进入wait状态
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if ("letterThread".equals(Thread.currentThread().getName())) {
                    // 打印字母A-Z
                    System.out.print((char) ('A' + i));
                    // 唤醒其他在等待的线程
                    LOCK.notifyAll();
                    try {
                        // 让当前线程释放锁资源，进入wait状态
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            LOCK.notifyAll();
        }
    }
}
