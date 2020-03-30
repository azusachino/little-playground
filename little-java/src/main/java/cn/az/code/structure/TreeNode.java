package cn.az.code.structure;

import lombok.Data;

/**
 * @author Liz
 * @version 2019/11/27
 */
@Data
public class TreeNode<T> {

    private T val;

    private TreeNode<T> parent;
    private TreeNode<T> left;
    private TreeNode<T> right;
}
