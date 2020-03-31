package cn.az.code.test;

import java.util.concurrent.*;

/**
 * PriorityBlockingQueue Question
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see PriorityBlockingQueue
 * @since 2020-03-16
 */
public class PriorityBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        func3();
    }

    public static void func1() throws InterruptedException {
        BlockingQueue<Integer> queue = new PriorityBlockingQueue<>(2);
        // 1. put(Object o) unblocking 不阻塞
        // 2. offer(Object o) 不限制
        // 3. 插入对象会做排序, 实现Comparable 或显式传Comparator
        queue.put(9);
        queue.put(8);
        queue.put(7);

        // 3
        System.out.println("queue.size() = " + queue.size());
        // 7, 8, 9 (natural order)
        System.out.println("queue.take() = " + queue.take());
        // [8,9]
        System.out.println("queue = " + queue);
    }

    public static void func2() throws InterruptedException {
        BlockingQueue<Integer> queue = new SynchronousQueue<>();
        // 1. no space, offer return false
        // 2. take() will blocking, 必须其他线程显示调用
        System.out.println("queue.offer(1) = " + queue.offer(1)); //false
        System.out.println("queue.offer(2) = " + queue.offer(2)); //false
        System.out.println("queue.offer(3) = " + queue.offer(3)); //false

        // blocking (no output)
        System.out.println("queue.take() = " + queue.take());
        System.out.println("queue.size() = " + queue.size());
    }

    public static void func3() throws InterruptedException {
       offer(new ArrayBlockingQueue<>(2));
       offer(new LinkedBlockingQueue<>(2));
       offer(new PriorityBlockingQueue<>(2));
       offer(new SynchronousQueue<>());
    }

    public static void offer(BlockingQueue<Integer> queue) throws InterruptedException {
        System.out.println("queue.getClass() = " + queue.getClass().getName());
        System.out.println("queue.offer(1) = " + queue.offer(1));
        System.out.println("queue.offer(2) = " + queue.offer(2));
        System.out.println("queue.offer(3) = " + queue.offer(3));

        System.out.println("queue.size() = " + queue.size());
        System.out.println("queue.take() = " + queue.take());
    }
}
