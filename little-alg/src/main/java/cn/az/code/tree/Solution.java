package cn.az.code.tree;

import java.util.LinkedList;

public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(5);
        Solution s = new Solution();
        String string = s.serializePreOrder(root);
        System.out.println(string);
        TreeNode other = s.deserializePreOrder(string);
        System.out.println(s.serializePreOrder(other));
    }

    public static final String SEP = ",";
    public static final String TOMB = "#";

    private int maxValue = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        this.maxPathDown(root);
        return this.maxValue;
    }

    private int maxPathDown(TreeNode node) {
        if (node == null)
            return 0;
        int left = Math.max(0, this.maxPathDown(node.left));
        int right = Math.max(0, this.maxPathDown(node.right));
        this.maxValue = Math.max(this.maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
    }

    public boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.val != b.val) {
            return false;
        }
        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

    public int calcTreeNodeCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + calcTreeNodeCount(root.left) + calcTreeNodeCount(root.right);
    }

    public int calcTreeNodeCountComplete(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int height = 0;
        while (root != null) {
            root = root.left;
            height++;
        }
        return (int) Math.pow(height, 2) - 1;
    }

    // O(lognlogn)
    public int calcTreeNodeCountFull(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode l = root;
        int lh = 0;
        while (l != null) {
            l = l.left;
            lh++;
        }

        TreeNode r = root;
        int rh = 0;
        while (r != null) {
            r = r.right;
            rh++;
        }

        if (lh == rh) {
            return (int) Math.pow(lh, 2) - 1;
        }
        return 1 + calcTreeNodeCountFull(root.left) + calcTreeNodeCountFull(root.right);
    }

    public String serializePreOrder(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helper(root, sb);
        return sb.toString();
    }

    public void helper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(TOMB).append(SEP);
            return;
        }
        sb.append(node.val).append(SEP);
        helper(node.left, sb);
        helper(node.right, sb);
    }

    public TreeNode deserializePreOrder(String s) {
        String[] ss = s.split(SEP);
        LinkedList<String> list = new LinkedList<>();
        for (String string : ss) {
            list.offer(string);
        }
        return helper(list);
    }

    public TreeNode helper(LinkedList<String> list) {
        if (list.isEmpty()) {
            return null;
        }
        String val = list.removeFirst();
        if (TOMB.equals(val)) {
            return null;
        }
        int rootVal = Integer.parseInt(val);
        TreeNode root = new TreeNode(rootVal);
        root.left = helper(list);
        root.right = helper(list);

        return root;
    }

}
