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
     * 插入排序
     * <p>
     * 1. 从第一个元素开始，该元素可以认为已经被排序
     * 2. 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 3. 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 4. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 5. 将新元素插入到该位置后
     * 6. 重复步骤2~5
     *
     * @param values 待排序数组
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
