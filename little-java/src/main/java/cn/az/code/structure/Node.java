package cn.az.code.structure;

import lombok.Data;

/**
 * @author Liz
 * @version 2019/11/27
 */
@Data
public class Node<T> {

    private T t;
    private Node<T> next;
}
