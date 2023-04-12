package cn.az.code.tree;

import java.util.LinkedList;

public class Solution2 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(5);
        Solution2 s = new Solution2();
        String ss = s.serializePost(root);
        System.out.println(ss);
        TreeNode other = s.deserializePost(ss);
        String sss = s.serializePost(other);
        System.out.println(sss);
    }

    public static final String SEP = ",";
    public static final String TOMB = "#";

    // #,#,1,#,#,2,3,#,#,5,4,

    public String serializePost(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helper(root, sb);
        return sb.toString();
    }

    public void helper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(TOMB).append(SEP);
            return;
        }
        helper(node.left, sb);
        helper(node.right, sb);
        sb.append(node.val).append(SEP);
    }

    public TreeNode deserializePost(String s) {
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
        String val = list.removeLast();
        if (TOMB.equals(val)) {
            return null;
        }
        int rootVal = Integer.parseInt(val);
        TreeNode root = new TreeNode(rootVal);

        root.right = helper(list);
        root.left = helper(list);

        return root;
    }

}
