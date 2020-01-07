package cn.az.code.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author: azusachino
 * @version: 2019/12/16
 */
public class ThreadTest {
    public static void main(String[] args) {
        CommonThreadFactory threadFactory = new CommonThreadFactory();
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("test-thread-%s").build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3,3,100L, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(10),threadFactory);
        ExecutorService service = Executors.newCachedThreadPool(factory);

        for (int i = 0; i < 5; i++) {
            service.execute(factory.newThread(new ThreadTask()));
        }
    }
}
