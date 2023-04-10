package cn.az.code.array;

public class AnotherSolution {

    public static void main(String[] args) {
        var s = new AnotherSolution();
        var nums = new int[] { 1, 2, 2, 4 };
        System.out.println(s.bs(nums, 2));
        System.out.println(s.bisect_left(nums, 2));
        System.out.println(s.bisect_right(nums, 2));
    }

    // sorted array
    public int removeDup(int[] nums) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                // reserve one as non-dup
                nums[slow++] = nums[fast];
            }
            fast++;
        }
        // indicates length
        return slow + 1;
    }

    public int removeElement(int[] nums, int val) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                // replace current
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    public void moveZeroes(int[] nums) {
        int slow = 0, fast = 0;
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

    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            char c = s[l];
            s[l] = s[r];
            s[r] = c;
            l++;
            r--;
        }
    }

    public int bs(int[] nums, int t) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == t) {
                return m;
            } else if (nums[m] > t) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }

    public int bisect_left(int nums[], int t) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] < t) {
                l = m + 1;
            } else {
                // keep shrink
                r = m;
            }
        }
        return l;
    }

    public int bisect_right(int nums[], int t) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] > t) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l - 1;
    }
}
