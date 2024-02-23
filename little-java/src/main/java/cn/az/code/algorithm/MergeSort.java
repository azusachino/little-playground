package cn.az.code.algorithm;

import java.util.Objects;

/**
 * MergeSort
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see Sort
 * @since 2020-03-24
 */
public class MergeSort<T extends Comparable<T>> implements Sort<T> {

    /**
     * 归并排序（递归）
     * <p>
     * ①. 将序列每相邻两个数字进行归并操作，形成 floor(n/2)个序列，排序后每个序列包含两个元素；
     * ②. 将上述序列再次归并，形成 floor(n/4)个序列，每个序列包含四个元素；
     * ③. 重复步骤②，直到所有元素排序完毕。
     * </p>
     *
     * @param values 待排序数组
     */
    @Override
    public void sort(T[] values) {
        if (Objects.isNull(values) || values.length == 0) {
            return;
        }
        int len = values.length;
        sort(values, 0, len - 1);
    }

    private void sort(T[] values, int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;
            sort(values, l, mid);
            sort(values, mid, r);
            merge(values, l, mid, r);
        }
    }

    private void merge(T[] values, int l, int mid, int r) {

    }
}
