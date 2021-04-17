package cn.az.code.algorithm;

import java.util.Arrays;

/**
 * @author az
 */
public class SelectSort implements Sort<Integer> {

    /**
     * 选择排序
     * <p>
     * 1. 从待排序序列中，找到关键字最小的元素；
     * 2. 如果最小元素不是待排序序列的第一个元素，将其和第一个元素互换；
     * 3. 从余下的 N - 1 个元素中，找出关键字最小的元素，重复①、②步，直到排序结束。
     * 仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     * </p>
     *
     * @param values 待排序数组
     */
    @Override
    public void sort(Integer[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < values.length; j++) {
                if (values[j] < values[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = values[min];
                values[min] = values[i];
                values[i] = temp;
                System.out.println("Sorting: " + Arrays.toString(values));
            }
        }
    }

    public static void main(String[] args) {
        Integer[] values = new Integer[]{1, 4, 2, 8, 5, 7, 3};
        SelectSort ss = new SelectSort();
        ss.sort(values);
    }
}
