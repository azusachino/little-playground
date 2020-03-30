package cn.az.code.concurrent;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author Liz
 * @date 1/14/2020
 */
public class VolatileVisibility {

    public static class Test{
        volatile int num = 0;
        public void updateNum() {
            num++;
        }
    }

    public static void main(String[] args) {
        ThreadFactory tf = ThreadFactoryBuilder.create().setNamePrefix("t-%d").build();
        ExecutorService service = Executors.newCachedThreadPool(tf);
        final Test t = new Test();
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                for (int j = 0; j < 1000; j++) {
                    t.updateNum();
                }
            });
        }
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println("t.num = " + t.num);
    }
}
