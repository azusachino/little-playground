package cn.az.code.tree;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public void addNode(int val) {
        TreeNode parent = null;
        var node = this;
        while (node != null) {
            parent = node;
            if (parent.val > val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        if (parent.val > val) {
            parent.left = new TreeNode(val);
        } else {
            parent.right = new TreeNode(val);
        }
    }

    public void deleteNode(int val) {

    }

    void deleteNode(TreeNode node, int val) {
        if (node == null) {
            return;
        }
        if (node.val == val) {
            if (node.left == null && node.right == null) {
                node = null;
                return;
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                var successor = node.right;
                while (successor.left != null) {
                    successor = successor.left;
                }
                node.val = successor.val;
                deleteNode(node.right, successor.val);
            }
        } else if (node.val > val) {
            deleteNode(node.left, val);
        } else {
            deleteNode(node.right, val);
        }
    }
}
