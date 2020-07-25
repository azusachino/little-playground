package cn.az.code.stream;

import cn.hutool.log.Log;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Liz
 * @date 2020/1/7
 */
public class CollectorsDemo {

    private static final Log LOG = Log.get(CollectorsDemo.class);

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        List<Dish> dishes = Arrays.asList(new Dish(), new Dish());
        LOG.debug(String.valueOf(dishes.stream().mapToInt(Dish::getCalories).sum()));

        dishes.stream().collect(Collectors.groupingBy(Dish::getName, Collectors.mapping(d -> {
            if (d.getName().endsWith("fish")) {
                return "fish";
            } else {
                return "meat";
            }
        }, Collectors.toCollection(HashSet::new))))
                .forEach(LOG::info);
    }
}
