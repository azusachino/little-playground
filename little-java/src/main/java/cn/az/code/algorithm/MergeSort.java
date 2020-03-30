package cn.az.code.algorithm;

import cn.hutool.core.util.ArrayUtil;

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
        sort(values, 0, len - 1);
    }

    private void sort(T[] values, int l, int r) {
        if (l < r) {
            int mid = (l + r)/ 2;
            sort (values, l, mid);
            sort(values, mid, r);
            merge(values, l, mid, r);
        }
    }

    private void merge(T[] values, int l, int mid, int r) {

    }
}
