package cn.az.code.impl;

import java.util.Arrays;

/**
 * @author ycpang
 * @since 2021-10-27 19:40
 */
public class MergeSort<T extends Comparable<T>> implements Sorter<T> {

    public static void main(String[] args) {
        MergeSort<String> s = new MergeSort<>();
        String[] ss = Sorter.of("a", "c", "d", "a", "b", "c", "p", "e");
        s.sort(ss);
    }

    /**
     * sort
     *
     * @param arr array
     */
    @Override
    public void sort(T[] arr) {
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private void sort(T[] arr, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, hi, mid);
    }

    private void merge(T[] arr, int lo, int hi, int mid) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                arr[k] = arr[j++];
            } else if (j > hi) {
                arr[k] = arr[i++];
            } else if (isLess(arr, j, i)) {
                arr[k] = arr[j++];
            } else {
                arr[k] = arr[i++];
            }
        }
    }
}
