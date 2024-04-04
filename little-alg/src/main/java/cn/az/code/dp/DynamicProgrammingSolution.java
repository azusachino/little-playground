package cn.az.code.dp;

import java.util.TreeMap;

/**
 * Dynamic Programming
 * 
 * @author haru
 * @since 2024-04-04
 */
public class DynamicProgrammingSolution {

    /**
     * odd even jumps
     * 
     * @link https://leetcode.com/problems/odd-even-jump
     * @param arr arr
     * @return good start index
     */
    public int oddEvenJumps(int[] arr) {
        String explain = """
                Take [5,1,3,4,2] as example.
                If we start at 2,
                we can jump either higher first or lower first to the end,
                because we are already at the end.
                higher(2) = true
                lower(2) = true

                If we start at 4,
                we can't jump higher, higher(4) = false
                we can jump lower to 2, lower(4) = higher(2) = true

                If we start at 3,
                we can jump higher to 4, higher(3) = lower(4) = true
                we can jump lower to 2, lower(3) = higher(2) = true

                If we start at 1,
                we can jump higher to 2, higher(1) = lower(2) = true
                we can't jump lower, lower(1) = false

                If we start at 5,
                we can't jump higher, higher(5) = false
                we can jump lower to 4, lower(5) = higher(4) = false
                """;
        System.out.println(explain);

        var n = arr.length;
        // constaint arr.length >= 1, at least one valid answer
        var res = 1;
        var even = new boolean[n];
        var odd = new boolean[n];
        // base case
        even[n - 1] = odd[n - 1] = true;

        var map = new TreeMap<Integer, Integer>();
        // use tree map to find ceiling, floor easily
        map.put(arr[n - 1], n - 1);

        for (var i = n - 2; i >= 0; --i) {
            var hi = map.ceilingEntry(arr[i]);
            var lo = map.floorEntry(arr[i]);
            if (hi != null) {
                // jump from ceiling
                even[i] = odd[hi.getValue()];
            }
            if (lo != null) {
                odd[i] = even[lo.getValue()];
            }
            if (even[i])
                res++;
            // every index was feasible last jump
            map.put(arr[i], i);
        }

        return res;
    }
}
