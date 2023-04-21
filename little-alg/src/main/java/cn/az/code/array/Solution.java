package cn.az.code.array;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

import cn.az.code.utils.GsonUtil;

public class Solution {

    public static void main(String[] args) {
        var s = new Solution();
        int[] nums = { 2, 7, 11, 16 };
        int[] r = s.twoSum(nums, 9);
        System.out.println(r[0] + "+" + r[1]);

        int[] A = { 1, 3, 4, 6, 1, 2 };
        GsonUtil.print(s.solution(A));
        GsonUtil.print(s.solution(new int[] { 1, 2, 3 }));
        GsonUtil.print(s.solution(new int[] { -1, -3 }));

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

    public int solution(int[] A) {
        Arrays.sort(A);
        int missing = 1;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != missing) {
                continue;
            } else {
                missing++;
            }
        }
        return missing;
    }

    public int solution1(int N) {
        String binary = Integer.toBinaryString(N);
        int ret = 0;
        int cnt = 0;

        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '0') {
                cnt++;
            } else {
                ret = Math.max(ret, cnt);
                cnt = 0;
            }

        }

        return ret;
    }

    public int solution3(int[] A) {
        // remain insert order
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (int i : A) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(1)) {
                return entry.getKey();
            }
        }

        return -1;
    }

    // nums = "----->-->"; k =3
    // result = "-->----->";

    // reverse "----->-->" we can get "<--<-----"
    // reverse "<--" we can get "--><-----"
    // reverse "<-----" we can get "-->----->"
    public int[] rotateK(int[] nums, int k) {
        int n = nums.length;
        k = k % nums.length;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
        return nums;
    }

    void reverse(int[] nums, int a, int b) {
        while (a < b) {
            int t = nums[a];
            nums[b] = nums[a];
            nums[a] = t;
            a++;
            b--;
        }
    }

    // fish competition
    public int solution(int[] A, int[] B) {
        int n = A.length;
        int eaten = 0;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (!st.isEmpty() && B[i] == 0) {
                if( st.peek() > A[i]) {
                    eaten++;
                } else {
                    while (!st.isEmpty() && st.peek() < A[i]) {
                        st.pop();
                        eaten++;
                    }
                    if (!st.isEmpty()) {
                        eaten++;
                    }
                }
            } else if (B[i] == 1) {
                st.push(A[i]);
            }

        }

        return n - eaten;
    }

}
