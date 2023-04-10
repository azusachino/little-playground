package cn.az.code.dp;

public class Solution {
    
    public int coinChange(int[] coins, int target) {
        return helper(coins, target);
    }

    public int helper(int[] coins, int target) {
        if (target < 0) {
            return -1;
        }
        if (target == 0) {
            return 0;
        }
        int r = Integer.MAX_VALUE;
        for (int c : coins) {
            int sub = helper(coins, target - c);
            if (sub == -1) {
                continue;
            }
            r = Math.min(r, 1 + sub);
        }
        return r;
    }
}
