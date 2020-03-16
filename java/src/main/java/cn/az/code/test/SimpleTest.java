package cn.az.code.test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * SimpleTest
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see Test
 * @since 2020-03-13
 */
public class SimpleTest {

    public static void main(String[] args) {
        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = mxBean.getAllThreadIds();

        Stream.of(threadIds).forEach(id -> System.out.println(Arrays.toString(mxBean.getThreadInfo(id))));
        a("aa");
        a(new SimpleTest());
        System.out.println("123***231".replaceAll("\\d", "*"));
    }

    public static <T> void a(T obj) {
        System.out.println(obj.toString());
    }
}
