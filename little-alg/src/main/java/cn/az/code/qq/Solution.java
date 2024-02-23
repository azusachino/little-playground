package cn.az.code.qq;

import cn.az.code.utils.GsonUtil;

public class Solution {

    public static void main(String[] args) {
        var s = new Solution();
        int[] nums = new int[] { 1, 3, -1, -3, 5, 3, 6, 7 };
        GsonUtil.print(s.maxSlidingWindow(nums, 3));
        GsonUtil.print(s.maxSlidingWindow(new int[] { 1 }, 1));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        MonoQueue mq = new MonoQueue();
        int[] ret = new int[n - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                mq.push(nums[i]);
            } else {
                mq.push(nums[i]);
                ret[i - k + 1] = mq.getMax();
                mq.pop(nums[i - k + 1]);
            }
        }
        return ret;
    }

}
