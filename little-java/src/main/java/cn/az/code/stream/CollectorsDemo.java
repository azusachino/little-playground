package cn.az.code.stream;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import cn.az.code.util.LogUtil;

/**
 * @author Liz
 */
public class CollectorsDemo {

    public static void main(String[] args) {
        List<Dish> dishes = Arrays.asList(new Dish(), new Dish());
        LogUtil.debug(String.valueOf(dishes.stream().mapToInt(Dish::getCalories).sum()));

        dishes.stream().collect(Collectors.groupingBy(Dish::getName, Collectors.mapping(d -> {
            if (d.getName().endsWith("fish")) {
                return "fish";
            } else {
                return "meat";
            }
        }, Collectors.toCollection(HashSet::new))))
                .forEach(LogUtil::info);
    }
}
