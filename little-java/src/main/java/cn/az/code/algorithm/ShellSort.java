package cn.az.code.algorithm;

import java.util.Arrays;

/**
 * @author az
 * @date 4/6/2020
 */
public class ShellSort implements Sort<Integer> {

    /**
     * 希尔排序（Wiki官方版）
     * <p>
     * 1. 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；（注意此算法的gap取值）
     * 2. 按增量序列个数k，对序列进行k 趟排序；
     * 3. 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
     * 仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     * </p>
     *
     * @param values 待排序数组
     */
    @Override
    public void sort(Integer[] values) {
        int gap = 1, temp, i, j, len = values.length;
        while (gap < len) {
            gap = gap * 3 + 1;
        }
        for (; gap > 0; gap /= 3) {
            for (i = gap; i < len; i++) {
                temp = values[i];
                for (j = i - gap; j >= 0 && values[j] > temp; j -= gap) {
                    values[j + gap] = values[j];
                }
                values[j + gap] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] test = new Integer[]{12, 2, 3, 5, 4, 3, 6};
        ShellSort shellSort = new ShellSort();
        shellSort.sort(test);
        System.out.println(Arrays.toString(test));
    }
}
