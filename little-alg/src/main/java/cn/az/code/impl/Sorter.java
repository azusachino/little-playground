package cn.az.code.impl;

/**
 * @author ycpang
 * @since 2021-10-27 19:20
 */
public interface Sorter<T extends Comparable<T>> {

    /**
     * sort
     *
     * @param arr array
     */
    void sort(T[] arr);

    /**
     * Of t [ ].
     *
     * @param <T>    the type parameter
     * @param values the values
     * @return the t [ ]
     */
    @SafeVarargs
    static <T> T[] of(T... values) {
        return values;
    }

    default boolean isLess(T[] comparables, int i, int j) {
        return comparables[i].compareTo(comparables[j]) < 0;
    }

    default void exch(T[] list, int i, int j) {
        T temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }
}
