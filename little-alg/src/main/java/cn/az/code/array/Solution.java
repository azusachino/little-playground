package cn.az.code.array;

public class Solution {

    public static void main(String[] args) {
        var s = new Solution();
        int[] nums = { 2, 7, 11, 16 };
        int[] r = s.twoSum(nums, 9);
        System.out.println(r[0] + "+" + r[1]);

    }

    public int removeDuplicates(int[] nums) {
        var replace_index = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[replace_index]) {
                replace_index++;
            }
            nums[replace_index] = nums[j];
        }
        return replace_index + 1;
    }

    int removeElement(int[] nums, int val) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    void moveZeroes(int[] nums) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        while (slow < nums.length) {
            nums[slow++] = 0;
        }
    }

    public void duplicateZeros(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                int j = arr.length - 1;
                while (j > i + 1) {
                    arr[j] = arr[j - 1];
                }
            }
        }
    }

    public int[] twoSum(int[] nums, int target) {
        // 一左一右两个指针相向而行
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                // 题目要求的索引是从 1 开始的
                return new int[] { left + 1, right + 1 };
            } else if (sum < target) {
                left++; // 让 sum 大一点
            } else if (sum > target) {
                right--; // 让 sum 小一点
            }
        }
        return new int[] { -1, -1 };
    }
}
