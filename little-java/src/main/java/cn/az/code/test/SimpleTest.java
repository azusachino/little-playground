package cn.az.code.test;

import cn.az.code.util.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.log.Log;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * SimpleTest
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @since 2020-03-13
 */
public class SimpleTest {

    private static final Log log = Log.get();

    int a;

    public void say() {

    }

    public static void main(String[] args) {
        printAbc2();
        // ExecutorService service = ThreadUtil.newExecutor(10);
        // for (int i = 0; i < 1000; i++) {
        // service.submit(() -> {
        // System.out.println(DateUtil.getCurrentDate());
        // });
        // }
        //
        // service.shutdown();
        test1();
        test2();
    }

    public static void printAbc() {
        ExecutorService service = ThreadUtil.newExecutor(3);

        for (int i = 0; i < 3; i++) {
            service.execute(() -> System.out.println("abc"));
        }

        service.shutdown();
    }

    public static void printAbc2() {
        Lock lock = new ReentrantLock(true);
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "abc");
                    c2.signal();
                    c1.await();
                }
            } catch (InterruptedException e) {
                log.error(e);
            } finally {
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            lock.lock();
            try {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "abc");
                    c3.signal();
                    c2.await();

                }
            } catch (InterruptedException e) {
                log.error(e);
            } finally {
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            lock.lock();
            try {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "abc");
                    c1.signal();
                    c3.await();

                }
            } catch (InterruptedException e) {
                log.error(e);
            } finally {
                lock.unlock();
            }
        }).start();

    }

    public static <T> void a(T obj) {
        System.out.println(obj.toString());
    }

    private static void test1() {
        System.out.println(DateUtil.getMonthBetweenYear(2020));

        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = mxBean.getAllThreadIds();

        Stream.of(threadIds).forEach(id -> System.out.println(Arrays.toString(mxBean.getThreadInfo(id))));
        a("aa");
        System.out.println("123***231".replaceAll("\\d", "*"));
    }

    private static void test2() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        int point = 3;
        // stream can't use return to stop execute
        list.forEach(i -> {
            if (i == 1) {
                log.info(String.valueOf(i));
            } else {
                log.warn(String.valueOf(i));
            }
        });

        // throw Exception
        list.forEach(i -> {
            if (i == point) {
                throw new NullPointerException("break the stream");
            } else {
                log.warn(String.valueOf(i));
            }
        });

        for (Integer i : list) {
            if (i == point) {
                return;
            } else {
                log.info("current val is {}", i);
            }
        }
        log.error("last sentence");
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
