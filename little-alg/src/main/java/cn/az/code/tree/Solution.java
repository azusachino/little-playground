package cn.az.code.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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
        s.myTree();
    }

    // https://dev.to/abdisalan_js/4-ways-to-traverse-binary-trees-with-animations-5bi5
    private TreeNode myTree() {
        TreeNode root = new TreeNode(42);
        root.left = new TreeNode(28);
        return root;
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

    // 114. Flatten tree
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        TreeNode l = root.left;
        TreeNode r = root.right;

        root.right = l;

        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = r;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return this.buildHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    public TreeNode buildHelper(int[] p, int ps, int pe, int[] i, int is, int ie, Map<Integer, Integer> map) {
        if (ps > pe || is > ie) {
            return null;
        }
        TreeNode root = new TreeNode(p[ps]);
        int inRootIndex = map.get(p[ps]);
        int leftIndex = inRootIndex - is;

        root.left = this.buildHelper(p, ps + 1, ps + inRootIndex, i, is, inRootIndex - 1, map);
        root.right = this.buildHelper(p, ps + leftIndex + 1, pe, i, inRootIndex + 1, ie, map);
        return root;
    }

    int preOrderIndex;
    Map<Integer, Integer> inOrderMap;

    public TreeNode buildTree1(int[] p, int[] i) {
        this.preOrderIndex = 0;
        this.inOrderMap = new HashMap<>();

        for (int index = 0; index < i.length; index++) {
            this.inOrderMap.put(i[index], index);
        }
        return arrayToTree(p, 0, p.length - 1);
    }

    public TreeNode arrayToTree(int[] preorder, int l, int r) {
        if (l > r) {
            return null;
        }
        int rootVal = preorder[this.preOrderIndex++];
        TreeNode root = new TreeNode(rootVal);

        root.left = arrayToTree(preorder, l, inOrderMap.get(rootVal) - 1);
        root.right = arrayToTree(preorder, inOrderMap.get(rootVal) + 1, r);

        return root;
    }

    public String levelSe(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode node = q.poll();
                if (node == null) {
                    sb.append(TOMB).append(SEP);
                    continue;
                }
                sb.append(node.val).append(SEP);
                q.offer(node.left);
                q.offer(node.right);
            }
        }

        return sb.toString();
    }

    public TreeNode levelDe(String s) {
        String[] nodes = s.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        for (int i = 1; i < nodes.length; i++) {
            // q only resverses parent node
            TreeNode parent = q.poll();
            String left = nodes[i++];
            if (left.equals(TOMB)) {
                parent.left = null;
            } else {
                parent.left = new TreeNode(Integer.parseInt(left));
                q.offer(parent.left);
            }
            String right = nodes[i++];
            if (right.equals(TOMB)) {
                parent.right = null;
            } else {
                parent.right = new TreeNode(Integer.parseInt(right));
                q.offer(parent.right);
            }
        }
        return root;
    }

    public TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }
        if (p == null || q == null) {
            return root;
        }

        TreeNode left = lca(root.left, p, q);
        var right = lca(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        if (left == null && right == null) {
            return null;
        }
        return left == null ? right : left;

    }
}
