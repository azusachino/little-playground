package cn.az.code.algorithm;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.log.Log;

import java.util.Arrays;
import java.util.Objects;

/**
 * BubbleSort
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see Sort
 * @since 2020-03-24
 */
public class BubbleSort<T extends Comparable<T>> implements Sort<T> {

    Log log = Log.get(BubbleSort.class);

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

        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (values[j].compareTo(values[j + 1]) > 0) {
                    T t = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = t;
                }
            }
            log.warn("round {}, array: {}", i + 1, values);
        }
    }

    public static void main(String[] args) {
        System.out.println("一般情况");
        Integer[] values = Sort.of(3, 1, 2, 5, 4);
        Sort<Integer> sort = new BubbleSort<>();
        sort.sort(values);
        System.out.printf("排序结果：%s\n", Arrays.toString(values));

        System.out.println("完全逆序");
        values = Sort.of(5, 4, 3, 2, 1);
        sort = new BubbleSort<>();
        sort.sort(values);
        System.out.printf("排序结果：%s\n", Arrays.toString(values));
    }
}
