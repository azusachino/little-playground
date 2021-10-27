package cn.az.code.impl;

import java.util.Arrays;

/**
 * @author ycpang
 * @since 2021-10-27 19:33
 */
public class InsertionSort<T extends Comparable<T>> implements Sorter<T> {

    public static void main(String[] args) {
        InsertionSort<String> s = new InsertionSort<>();
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
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (isLess(arr, j, j - 1)) {
                    exch(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
