package cn.az.code.guava;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author az
 * @since 08/31/20
 */
public class GuavaCollectionTest {

    public static void main(String[] args) {
        Set<Integer> set = Sets.union(Sets.newHashSet(1, 2, 3), Sets.newHashSetWithExpectedSize(4));
        Sets.filter(set, x -> (x & 1) == 0).forEach(System.out::println);
        Sets.symmetricDifference(Sets.newHashSet(1, 2, 3), Sets.newHashSet(2, 3, 4));
        System.out.println(Sets.powerSet(Sets.newHashSet(1,2,3)));
    }
}
