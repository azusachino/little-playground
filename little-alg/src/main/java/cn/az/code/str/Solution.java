package cn.az.code.str;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import cn.az.code.utils.GsonUtil;

/**
 * String Problems
 */
public class Solution {

    public static void main(String[] args) {
        var s = new Solution();
        String a = "[{}]";
        System.out.println(s.isValid(a));
        String p = "acaba";
        // System.out.println(s.longestPalindrome(p));
        System.out.println(s.isPalindrome(p));
        var ss = "A man, a plan, a canal: Panama";
        System.out.println(s.isPalindrome1(ss));
        GsonUtil.print(s.mergeAlternately("abcd", "pq"));
    }

    public boolean isValid(String s) {
        // Map<Character, Character> map = Map.of(']', '[', '}', '{', ')', '(');
        Map<Character, Character> map = Map.of('[', ']', '{', '}', '(', ')');
        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                st.push(map.get(c));
            } else {
                if (st.isEmpty() || !st.peek().equals(c)) {
                    return false;
                } else {
                    st.pop();
                }
            }
        }
        return st.isEmpty();
    }

    public String longestPalindrome(String s) {
        String ret = "";

        for (int i = 0; i < s.length(); i++) {
            String odd = palindrome(s, i, i);
            String even = palindrome(s, i, i + 1);
            if (ret.length() < odd.length()) {
                ret = odd;
            }
            if (ret.length() < even.length()) {
                ret = even;
            }

        }
        return ret;
    }

    public String palindrome(String s, int l, int r) {
        char[] chars = s.toCharArray();
        while (l >= 0 && r < s.length() && chars[l] == chars[r]) {
            l--;
            r++;
        }
        System.out.println(l + " " + r);
        return s.substring(l + 1, r - 1);
    }

    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int l = 0, r = s.length() - 1;
        char[] chars = s.toCharArray();
        while (l < r) {
            if (chars[l++] != chars[r--]) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome1(String s) {
        // remove all non alpha & num
        if (s.isEmpty()) {
            return true;
        }
        char[] chars = s.toCharArray();
        int i = 0;
        for (int j = 0; j < chars.length; j++) {
            if (Character.isAlphabetic(chars[j]) || Character.isDigit(chars[j])) {
                chars[i++] = Character.toLowerCase(chars[j]);
            }
        }
        char[] new_chars = new char[i];
        for (int j = 0; j < new_chars.length; j++) {
            new_chars[j] = chars[j];
        }
        String ns = String.copyValueOf(new_chars);
        return isPalindrome(ns);
    }

    public int longestPalindromeSimpleVer(String s) {
        if (null == s || s.isEmpty()) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int evenTimes = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (set.contains(c)) {
                set.remove(c);
                evenTimes++;
            } else {
                set.add(c);
            }
        }
        if (set.isEmpty()) {
            return 2 * evenTimes;
        }
        // set contains all odd element, we only need one
        return 2 * evenTimes + 1;
    }

    // if (s[i] == s[j]) 它俩一定在最长回文子序列中
    // dp[i][j] = dp[i + 1][j - 1] + 2;
    // else
    // s[i+1..j] 和 s[i..j-1] 谁的回文子序列更长？
    // dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        // base case, every char is palindrome
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int l = 0, r = 0;
        while (l < word1.length() && r < word2.length()) {
            if (l < word1.length()) {
                sb.append(word1.charAt(l++));
            }
            if (r < word2.length()) {
                sb.append(word2.charAt(r++));
            }
        }
        while (l < word1.length()) {
            sb.append(word1.charAt(l++));
        }

        while (r < word2.length()) {
            sb.append(word2.charAt(r++));
        }
        return sb.toString();
    }
}
