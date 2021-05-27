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

    /**
     * 冒泡排序
     * <p>
     * ①. 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * ②. 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
     * ③. 针对所有的元素重复以上的步骤，除了最后一个。
     * ④. 持续每次对越来越少的元素重复上面的步骤①~③，直到没有任何一对数字需要比较。
     * </p>
     *
     * @param values 待排序数组
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

}
