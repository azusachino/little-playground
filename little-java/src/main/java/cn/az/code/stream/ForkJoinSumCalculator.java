package cn.az.code.stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author Liz
 */

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] nums;
    private final int start;
    private final int end;

    public ForkJoinSumCalculator(long[] nums, int start, int end) {
        this.nums = nums;
        this.start = start;
        this.end = end;
    }

    public long[] getNums() {
        return nums;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    private static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] nums) {
        this(nums, 0, nums.length);
    }

    public static void main(String[] args) {
        ParallelDemo.measurePerformance(ForkJoinSumCalculator::forkJoinSum, 10_100_100L);
    }

    @Override
    protected Long compute() {
        int len = end - start;
        if (len == THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalculator leftFork = new ForkJoinSumCalculator(nums, start, start + len / 2);
        leftFork.fork();
        ForkJoinSumCalculator rightFork = new ForkJoinSumCalculator(nums, start + len / 2, end);
        Long rightResult = rightFork.compute();
        Long leftResult = leftFork.join();
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += nums[i];
        }
        return sum;
    }

    public static long forkJoinSum(Long n) {
        long[] nums = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(nums);
        return FORK_JOIN_POOL.invoke(task);
    }
}
