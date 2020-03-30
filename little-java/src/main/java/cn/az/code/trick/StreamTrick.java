package cn.az.code.trick;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The type Stream trick.
 *
 * @author azusachino
 * @version 2019/12/07
 */
public class StreamTrick {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Stream<String> stream;
        //由单独的值构成
        Stream<String> strStream = Stream.of("one", "two", "three", "four");

        //由数组构成
        String [] strArray = new String[] {"a", "bb", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);

        //由集合构成，最常用了
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

        //对于基本数值型，目前有三种对应的包装类型的Stream：IntStream、LongStream、DoubleStream
        IntStream.of(1, 2, 3).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);

        //生成100以内的15个随机整数，用来构造测试随机数不失为一种简便的方式
        Stream.generate(() -> new Random().nextInt(100)).limit(15).forEach(System.out::println);

        //Another way
        IntStream.generate(() -> (int) (System.nanoTime() % 100)).
                limit(15).forEach(System.out::println);

        //random其实提供了更方便的ints()方法
        new Random().ints().limit(15).forEach(System.out::println);

        // 1. 转换为数组
        String[] strArray1 = stream.toArray(String[]::new);
        // 2. 转换为集合
        List<String> list1 = stream.collect(Collectors.toList());
        List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
        Set<String> set1 = stream.collect(Collectors.toSet());
        Stack<String> stack1 = stream.collect(Collectors.toCollection(Stack::new));
        // 3. 转为String
        String str = stream.collect(Collectors.joining(","));

        Iterable<String> iterable = new ArrayList<>();
        System.out.println(Lists.newArrayList(iterable));
    }
}
