package cn.az.code.algorithm;

import java.util.Arrays;

/**
 * @author az
 */
public class HeapSort implements Sort<Integer> {

    /**
     * 堆排序
     * <p>
     * 1. 先将初始序列K[1..n]建成一个大顶堆, 那么此时第一个元素K1最大, 此堆为初始的无序区.
     * 2. 再将关键字最大的记录K1 (即堆顶, 第一个元素)和无序区的最后一个记录 Kn 交换, 由此得到新的无序区K[1..n−1]和有序区K[n], 且满足K[1..n−1].keys⩽K[n].key
     * 3. 交换K1 和 Kn 后, 堆顶可能违反堆性质, 因此需将K[1..n−1]调整为堆. 然后重复步骤②, 直到无序区只有一个元素时停止.
     * </p>
     *
     * @param values 待排序数组
     */
    @Override
    public void sort(Integer[] values) {
        for (int i = values.length; i > 0; i--) {
            maxHeapFy(values, i);

            int temp = values[0];
            values[0] = values[i - 1];
            values[i - 1] = temp;
        }
    }

    private void maxHeapFy(Integer[] arr, int limit) {
        if (arr.length <= 0 || arr.length < limit) {
            return;
        }
        int parentIdx = limit / 2;

        for (; parentIdx >= 0; parentIdx--) {
            if (parentIdx * 2 >= limit) {
                continue;
            }
            //左子节点位置
            int left = parentIdx * 2;
            //右子节点位置，如果没有右节点，默认为左节点位置
            int right = (left + 1) >= limit ? left : (left + 1);

            int maxChildId = arr[left] >= arr[right] ? left : right;
            //交换父节点与左右子节点中的最大值
            if (arr[maxChildId] > arr[parentIdx]) {
                int temp = arr[parentIdx];
                arr[parentIdx] = arr[maxChildId];
                arr[maxChildId] = temp;
            }
        }
        System.out.println("Max_Heapify: " + Arrays.toString(arr));
    }

}
