package cn.az.code.math;

import java.util.LinkedList;

import cn.az.code.utils.GsonUtil;

public class Solution {

    public static void main(String[] args) {
        var s = new Solution();
        GsonUtil.print(s.reverse(-123));
    }

    public int reverse(int x) {
        int o = 1;
        if (x < 0) {
            o = -1;
            x = -x;
        }
        int ret = 0;
        while (x > 0) {
            int sup = x % 10;
            ret = (ret * 10) + sup;
            x /= 10;
        }
        return ret * o;
    }

    public int myPow(int a, int[] b) {

        LinkedList<Integer> list = new LinkedList<>();
        for (int i : b) {
            list.add(i);
        }

        return helper(a, list);
    }

    int helper(int a, LinkedList<Integer> b) {
        if (b.isEmpty()) {
            return 1;
        }
        int current = b.removeLast();
        double v = mypow(a, current);
        int v2 = mypow(helper(a, b), 10);

        return (int) v + v2;
    }

    int base = 1337;

    int mypow(int a, int b) {
        if (b == 0){
            return 1;
        }
        a %= base;
        if (b % 2 == 1) {
            return (a *mypow(a, b-1)) % base;
        } else {
            int sub = mypow(a, b/2);
            return (sub * sub) % base; 
        }
    }
}
