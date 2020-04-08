package cn.az.code.algorithm;

import cn.hutool.core.util.ArrayUtil;

import java.util.Arrays;
import java.util.Objects;

/**
 * QuickSort
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see Sort
 * @since 2020-03-24
 */
public class QuickSort<T extends Comparable<T>> implements Sort<T> {

    /**
     * Sort.
     *
     * @param values the values
     */
    @Override
    public void sort(T[] values) {
        if (Objects.isNull(values) || ArrayUtil.isEmpty(values)) {
            return;
        }
        int len = values.length;
        int l = 0, r = len - 1;

        sort(values, l, r);
    }

    private void sort(T[] values, int l, int r) {
        if (l < r) {
            int pivot = partition(values, l, r);
            sort(values, l, pivot - 1);
            sort(values, pivot + 1, r);
        }
    }

    /**
     * 快速排序（递归）
     * <p>
     * ①. 从数列中挑出一个元素，称为"基准"（pivot）。
     * ②. 重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（相同的数可以到任一边）。在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
     * ③. 递归地（recursively）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     * </p>
     *
     * @param values 待排序数组
     * @param l      左边界
     * @param r      右边界
     */
    private int partition(T[] values, int l, int r) {
        T pivot = values[r];
        int low = l;

        for (int i = low; i < r; i++) {
            if (values[i].compareTo(pivot) < 1) {
                T temp = values[low];
                values[low] = values[i];
                values[i] = temp;
                low++;
            }
        }
        T temp = values[low];
        values[low] = values[r];
        values[r] = temp;

        return low;
    }

    public static void main(String[] args) {
        Integer[] values = Sort.of(2, 5, 6, 7, 8, 8, 9, 2, 1, 6, 7, 5, 6, 11, 23);
        Sort<Integer> sort = new QuickSort<>();
        sort.sort(values);
        System.out.println(Arrays.asList(values));
    }
}
