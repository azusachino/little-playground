package cn.az.code.function;

/**
 * The interface Throwing consumer.
 *
 * @param <T> the type parameter
 * @param <E> the type parameter
 * @author Liz
 * @date 1 /7/2020
 */
@FunctionalInterface
public interface ThrowingConsumer<T,E extends Exception> {

    /**
     * Accept.
     *
     * @param t the t
     * @throws E the e
     */
    void accept(T t) throws E;
}
