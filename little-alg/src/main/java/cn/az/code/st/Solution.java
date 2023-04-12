package cn.az.code.st;

import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        var s = new Solution();
        System.out.println(s.removeStars("leet**cod*e"));
        System.out.println(s.removeStars("erase*****"));
    }

    public String removeStars(String s) {
        if (s.isEmpty()) {
            return s;
        }
        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '*' && !s.isEmpty()) {
                st.pop();
            } else {
                st.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : st) {
            sb.append(c);
        }
        return sb.toString();
    }
}
