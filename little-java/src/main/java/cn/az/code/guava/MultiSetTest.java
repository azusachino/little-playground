package cn.az.code.guava;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Chars;

/**
 * @author az
 * @since 08/31/20
 */
public class MultiSetTest {

    public static void main(String[] args) {
        String txt = "我欲乘风归去, 你知不知道呀";
        Multiset<Character> multiset = HashMultiset.create();
        multiset.addAll(Chars.asList(txt.toCharArray()));
        System.out.println(multiset.size());
        System.out.println(multiset.count('我'));
    }
}
