package cn.az.code.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Liz
 * @date 2020/1/7
 */
public class ThreadId {
    private static final AtomicInteger NEXT_ID = new AtomicInteger(0);

    private static final ThreadLocal<Integer> THREAD_ID = ThreadLocal.withInitial(NEXT_ID::getAndIncrement);

    public static int get() {
        return THREAD_ID.get();
    }
}
