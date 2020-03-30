package cn.az.code.algorithm;

import cn.hutool.core.util.ArrayUtil;

import java.util.Arrays;
import java.util.Objects;

/**
 * InsertSort
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see Sort
 * @since 2020-03-24
 */
public class InsertSort<T extends Comparable<T>> implements Sort<T> {

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

        for (int i = 1; i < len; i++) {
            T t = values[i];
            int j = i;
            while (j > 0 && t.compareTo(values[j - 1]) < 0) {
                values[j] = values[j - 1];
                j--;
            }
            values[j] = t;
            System.out.printf("第%d轮：%s\n", i, Arrays.toString(values));
        }
    }

    public static void main(String[] args) {
        System.out.println("一般情况");
        Integer[] values = Sort.of(3, 2, 1, 5, 4);
        Sort<Integer> sort = new InsertSort<>();
        sort.sort(values);
        System.out.println(Arrays.toString(values));

        System.out.println("完全逆序");
        values = Sort.of(5, 4, 3, 2, 1);
        sort = new InsertSort<>();
        sort.sort(values);
        System.out.println(Arrays.toString(values));
    }
}
