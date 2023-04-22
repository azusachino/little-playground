package cn.az.code.str;

import cn.az.code.utils.GsonUtil;

public class Solution2 {

    public static void main(String[] args) {
        var s = new Solution2();
        GsonUtil.print(s.isPalindrome(""));
        GsonUtil.print(s.longestPalindrome("askjhdiadkhsdfil"));
    }

    public boolean isPalindrome(String s) {
        var l = 0;
        var r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }

    public String longestPalindrome(String s) {
        String ret = "";
        for (int i = 0; i < s.length(); i++) {
            String odd = palin(s, i, i);
            String even = palin(s, i, i + 1);
            if (ret.length() < odd.length()) {
                ret = odd;
            }
            if (ret.length() < even.length()) {
                ret = even;
            }
        }

        return ret;
    }

    public String palin(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return s.substring(i + 1, j - 1);
    }

}
