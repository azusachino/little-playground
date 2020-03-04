package cn.az.code.thread;

/**
 * @author az
 * @date 2020/3/1
 */
public class Basic {

    public static ThreadLocal<Long> x = ThreadLocal.withInitial(() -> {
        System.out.println("Initializing...");
        return Thread.currentThread().getId();
    });

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                System.out.println(x.get());
            }
        }.start();
        x.set(110L);
        System.out.println(x.get());
    }
}
