package cn.az.code.design;

import java.util.Arrays;

/**
 * @author az
 * @date 2020-03-06
 */
public class SimpleTest {

    public static void main(String[] args) {

        NutritionFacts facts = new NutritionFacts.Builder(1, 2).build();
        Singleton6 s6 = Singleton6.INSTANCE;
        Singleton5 s5 = Singleton5.getInstance();
        Singleton4 s4 = Singleton4.getInstance();
        Singleton3 s3 = Singleton3.getInstance();
        Singleton2 s2 = Singleton2.getInstance();
        Singleton1 s1 = Singleton1.getInstance();

        Arrays.asList(s1, s2, s3, s4, s5, s6).forEach(System.out::println);
    }
}
