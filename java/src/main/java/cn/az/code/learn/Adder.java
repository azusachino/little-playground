package cn.az.code.learn;

/**
 * The interface Adder.
 *
 * @param <T> the type parameter
 * @author azusachino
 * @version 12 /12/2019
 */
@FunctionalInterface
public interface Adder<T> {
    /**
     * Add int.
     *
     * @param a the a
     * @param b the b
     * @return the int
     */
    T add(T a, T b);
}
