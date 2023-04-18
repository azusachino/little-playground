package cn.az.code.math;

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
}
