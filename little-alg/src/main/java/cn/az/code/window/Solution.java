package cn.az.code.window;

import java.util.HashMap;
import java.util.Map;

import cn.az.code.utils.GsonUtil;

public class Solution {

    public static void main(String[] args) {
        var s = new Solution();
        GsonUtil.print(s.minWindow("ebbancf", "abc"));
        GsonUtil.print(s.lengthOfLongestSubString("aabab"));
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> window = new HashMap<>();
        int l = 0, r = 0;
        int validCnt = 0;
        int start = 0, len = Integer.MAX_VALUE;
        char[] chars = s.toCharArray();
        while (r < s.length()) {
            char c = chars[r++];
            // grow the window
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    validCnt++;
                }
            }

            // the timing for shrink
            while (validCnt == need.size()) {
                // update final result
                if (r - l < len) {
                    start = l;
                    len = r - l;
                }
                // shrink the window
                char d = chars[l++];
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        validCnt--;
                    }
                    window.computeIfPresent(d, (k, v) -> v - 1);
                }

            }
        }

        if (len == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(start, start + len);
    }

    int lengthOfLongestSubString(String s) {
        Map<Character, Integer> window = new HashMap<>();
        int l = 0, r = 0;
        int ret = 0;

        char[] chars = s.toCharArray();
        while (r < s.length()) {
            char c = chars[r++];
            window.put(c, window.getOrDefault(c, 0) + 1);

            // when to shrink
            while (window.get(c) > 1) {
                char d = chars[l++];
                window.computeIfPresent(d, (k, v) -> v - 1);
            }

            ret = Math.max(ret, r - l);
        }

        return ret;
    }
}
