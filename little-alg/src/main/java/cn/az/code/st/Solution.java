package cn.az.code.st;

import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        var s = new Solution();
        System.out.println(s.removeStars("leet**cod*e"));
        System.out.println(s.removeStars("erase*****"));
        System.out.println(s.path("/home/../abc"));
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

    public String path(String p) {
        if (p.isEmpty()) {
            return p;
        }
        Stack<String> st = new Stack<>();
        for (String s : p.split("/")) {
            st.push(s);
        }
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            String pk = st.pop();
            if (pk.equals(".") || pk.isEmpty()) {
                continue;
            }
            if (pk.equals("..")) {
                st.pop();
            } else {
                sb.append("/").append(pk);
            }
        }

        if (sb.isEmpty()) {
            return "/";
        } else {
            return sb.toString();
        }
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> st = new Stack<>();
        int j = 0;
        for (int v : pushed) {
            st.push(v);
            while (!st.isEmpty() && st.peek() == popped[j]) {
                st.pop();
                j++;
            }
        }

        return st.isEmpty();
    }

    public boolean validateStackSequences1(int[] pushed, int[] popped) {

        int i = 0, j = 0;
        for (int v : pushed) {
            pushed[i++] = v;
            while (i > 0 && pushed[i - 1] == popped[j]) {
                i--;
                j++;
            }
        }

        return i == 0;
    }
}
