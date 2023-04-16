package cn.az.code.bfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import cn.az.code.tree.TreeNode;
import cn.az.code.utils.GsonUtil;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        List<String> dead = Arrays.asList("1234", "5678");
        GsonUtil.print(s.openLock(dead, "0009"));
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int steps = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode node = q.poll();
                if (node.left == null && node.right == null) {
                    return steps;
                }
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            steps++;
        }

        return steps;
    }

    public int openLock(List<String> deadends, String target) {
        Set<String> set = new HashSet<>();
        for (String string : deadends) {
            set.add(string);
        }
        
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer("0000");
        visited.add("0000");
        int steps = 0;

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                if (set.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) {
                    return steps;
                }
                for (int j = 0; j < 4; j++) {
                    String plus = plusOne(cur, j);
                    if (!visited.contains(plus)) {
                        visited.add(plus);
                        q.offer(plus);
                    }
                    String minus = minusOne(cur, j);
                    if (!visited.contains(minus)) {
                        visited.add(minus);
                        q.offer(minus);
                    }
                }
            }
            steps++;
        }

        return -1;
    }

    public String plusOne(String s, int i) {
        char[] chars = s.toCharArray();
        if (chars[i] == '9') {
            chars[i] = '0';
        } else {
            chars[i] += 1;
        }
        return chars.toString();
    }

    public String minusOne(String s, int i) {
        char[] chars = s.toCharArray();
        if (chars[i] == '0') {
            chars[i] = '9';
        } else {
            chars[i] -= 1;
        }
        return chars.toString();
    }

}
