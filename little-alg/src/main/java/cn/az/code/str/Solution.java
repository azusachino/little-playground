package cn.az.code.str;

import java.util.Map;
import java.util.Stack;

/**
 * String Problems
 */
public class Solution {

    public static void main(String[] args) {
        var s = new Solution();
        String a = "[{}]";
        System.out.println(s.isValid(a));
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
}
