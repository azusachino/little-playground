package cn.az.code.concurrent;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * AddTask
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see RecursiveTask
 * @since 2020-03-17
 */
public class AddTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 20;
    private int[] arr;
    private int start;
    private int end;

    public AddTask(int[] arr, int start, int end) {
        // 累加从start到end的数组元素
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        // 当end与start之间的差小于THRESHOLD时，开始进行实际累加
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        } else {
            // 如果当end与start之间的差大于THRESHOLD时，即要打印的数超过20个
            // 将大任务分解成两个小任务。
            int middle = (start + end) / 2;
            AddTask left = new AddTask(arr, start, middle);
            AddTask right = new AddTask(arr, middle, end);
            // 并行执行两个“小任务”
            left.fork();
            right.fork();
            // 把两个“小任务”累加的结果合并起来
            return left.join() + right.join();
        }
    }

    public static class Sum {
        public static void main(String[] args)
                throws Exception {
            int[] arr = new int[100];
            Random rand = new Random();
            int total = 0;
            // 初始化100个数字元素
            for (int i = 0, len = arr.length; i < len; i++) {
                int tmp = rand.nextInt(20);
                // 对数组元素赋值，并将数组元素的值添加到total总和中。
                total += (arr[i] = tmp);
            }
            System.out.println(total);

            ForkJoinPool pool = new ForkJoinPool();
            // 提交可分解的AddTask任务
            Future<Integer> future = pool.submit(new AddTask(arr, 0, arr.length));
            System.out.println(future.get());
            // 关闭线程池
            pool.shutdown();
        }
    }

}