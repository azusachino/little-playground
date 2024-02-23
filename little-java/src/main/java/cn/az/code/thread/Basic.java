package cn.az.code.thread;

/**
 * @author az
 */
public class Basic {

    public static ThreadLocal<Long> x = ThreadLocal.withInitial(() -> {
        System.out.println("Initializing...");
        return Thread.currentThread().threadId();
    });

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    System.out.println(x.get());
                }
            }.start();
            x.set(i * 10L);
            System.out.println(x.get());
        }

    }
}
