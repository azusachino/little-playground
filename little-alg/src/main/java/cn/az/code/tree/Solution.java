package cn.az.code.tree;

public class Solution {

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
}
