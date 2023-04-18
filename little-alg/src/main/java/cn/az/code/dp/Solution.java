package cn.az.code.dp;

import java.util.Arrays;

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

    public int longestIncreaseSeq(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        // base case
        Arrays.fill(dp, 1);

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public int maxSubSequence(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        // base case
        dp[0] = nums[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }

        return Arrays.stream(dp).max().getAsInt();
    }
}
