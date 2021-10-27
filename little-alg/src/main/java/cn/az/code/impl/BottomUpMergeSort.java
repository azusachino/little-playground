package cn.az.code.impl;

/**
 * @author ycpang
 * @since 2021-10-27 20:24
 */
public class BottomUpMergeSort<T extends Comparable<T>> implements Sorter<T> {

    /**
     * sort
     *
     * @param arr array
     */
    @Override
    public void sort(T[] arr) {
        int n = arr.length;
        T[] aux = Sorter.of();
        for (int i = 1; i < n; i = i * 2) {
            for (int j = 0; j < n - i; j += i * 2) {
                merge(arr, aux, j, j + i - 1, Math.min(j + i + i - 1, n - 1));
            }
        }
    }

    private void merge(T[] arr, T[] aux, int lo, int mid, int hi) {
    }
}
