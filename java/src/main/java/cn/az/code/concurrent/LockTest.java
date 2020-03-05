package cn.az.code.concurrent;

import cn.hutool.core.thread.ExecutorBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author az
 * @date 2020-03-04
 */
public class LockTest {

    public static void main(String[] args) {

        final BoundedBuffer boundedBuffer = new BoundedBuffer();
        ExecutorService service = ExecutorBuilder.create().setCorePoolSize(2).build();

        service.execute(() -> {
            System.out.println("t1 run");
            for (int i = 0; i < 20; i++) {
                try {
                    System.out.println("putting...");
                    boundedBuffer.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        service.execute(() -> {
            System.out.println("t2 run");
            for (int i = 0; i < 20; i++) {
                try {
                    Object val = boundedBuffer.take();
                    System.out.println("getting..." + val);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    static class BoundedBuffer {
        final Lock lock = new ReentrantLock();
        final Condition notFull = lock.newCondition();
        final Condition notEmpty = lock.newCondition();

        final Object[] items = new Object[15];
        //写索引, 读索引, 队列中存在的数据个数;
        int putPtr, takePtr, count;

        public void put(Object x) throws InterruptedException {
            System.out.println("put wait lock");
            lock.lock();
            try {
                System.out.println("put get lock");
                while (count == items.length) {
                    System.out.println("buffer full, please wait");
                    notFull.await();
                }
                items[putPtr] = x;
                if (++putPtr == items.length) {
                    putPtr = 0;
                }
                ++count;
                System.out.println("----------------------" + x);
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }


        public Object take() throws InterruptedException {
            System.out.println("take wait lock");
            lock.lock();
            try {
                System.out.println("take get lock");
                while (count == 0) {
                    System.out.println("no elements, please wait");
                    notEmpty.await();
                }
                System.out.println("--------------------被唤醒");
                Object x = items[takePtr];
                if (++takePtr == items.length) {
                    takePtr = 0;
                }
                --count;
                notFull.signal();
                return x;
            } finally {
                lock.unlock();
            }
        }
    }
}
