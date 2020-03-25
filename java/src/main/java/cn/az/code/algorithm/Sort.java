package cn.az.code.algorithm;

/**
 * Sort
 *
 * @param <T> the type parameter
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see Comparable
 * @since 2020-03-24
 */
public interface Sort<T extends Comparable<T>> {

    /**
     * Sort.
     *
     * @param values the values
     */
    void sort(T[] values);

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
}
