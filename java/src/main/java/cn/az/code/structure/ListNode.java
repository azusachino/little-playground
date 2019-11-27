package cn.az.code.structure;

import lombok.Data;

/**
 * @author Liz
 * @version 2019/11/27
 */
@Data
public class ListNode<T> {

    private T val;
    private ListNode<T> prev;
    private ListNode<T> next;
}
