package cn.az.code.impl;

import java.util.Arrays;

/**
 * @author ycpang
 * @since 2021-10-27 19:20
 */
public class SelectionSort<T extends Comparable<T>> implements Sorter<T> {

    public static void main(String[] args) {
        SelectionSort<String> s = new SelectionSort<>();
        String[] ss = Sorter.of("a", "c", "d", "a", "b");
        s.sort(ss);
    }

    /**
     * sort
     *
     * @param arr array
     */
    @Override
    public void sort(T[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (isLess(arr, j, min)) {
                    min = j;
                }
            }
            exch(arr, i, min);
        }
        System.out.println(Arrays.toString(arr));
    }
}
